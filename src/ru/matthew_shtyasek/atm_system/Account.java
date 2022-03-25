package ru.matthew_shtyasek.atm_system;

import ru.matthew_shtyasek.atm_system.exception.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.Random;

public class Account {
    private static final Random random = new Random();

    private long uuid;
    private double balance;
    private User holder;
    private boolean isBlocked;
    private ArrayList<Transaction> transactions;

    public Account(final long uuid, final User holder) {
        this.load(uuid, holder);
    }

    private void load(final long uuid, final User holder) {
        this.uuid = uuid; //todo: load from the database
        this.balance = random.nextInt(1000000);
        this.holder = holder;
        this.isBlocked = false;
    }

    public boolean transfer(final Account recipient, final int amount) throws NotEnoughMoneyException {
        return transfer(recipient, amount, "");
    }

    public boolean transfer(final Account recipient, final int amount, final String message) throws NotEnoughMoneyException {
        this.transactions.add(new Transaction(this, recipient, amount, message));
        return this.transactions.get(this.transactions.size() - 1).start();
    }

    public void takeMoney(final int amount) throws NotEnoughMoneyException {
        if (!this.hasMoney(amount))
            throw new NotEnoughMoneyException();
        this.balance -= amount;
    }

    public void addMoney(final int amount) {
        this.balance += amount;
    }

    public boolean hasMoney(final int amount) {
        return this.balance > amount;
    }

    //============================================SETTERS======================================

    //============================================GETTERS======================================

    public double getBalance() {
        return this.balance;
    }

    public boolean getIsBlocked() {
        return this.isBlocked;
    }

    public User getHolder() {
        return this.holder;
    }
}
