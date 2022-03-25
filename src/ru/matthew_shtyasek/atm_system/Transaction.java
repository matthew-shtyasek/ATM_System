package ru.matthew_shtyasek.atm_system;

import ru.matthew_shtyasek.atm_system.exception.NotEnoughMoneyException;

import java.util.Date;

public class Transaction {
    private int amount;
    private Date timestamp;
    private String message;
    private Account sender;
    private Account recipient;
    private TransactionOutcomes transactionOutcomes;

    public Transaction(final Account sender, final Account recipient, final int amount) throws NotEnoughMoneyException {
        this(sender, recipient, amount, "");
    }

    public Transaction(final Account sender, final Account recipient, final int amount, final String message) throws NotEnoughMoneyException {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.message = message;
        this.timestamp = new Date();
        this.start();
    }

    public boolean start() throws NotEnoughMoneyException {
        if (this.sender.getIsBlocked())
            this.transactionOutcomes = TransactionOutcomes.SENDER_BLOCKED;
        else if (this.recipient.getIsBlocked())
            this.transactionOutcomes = TransactionOutcomes.RECIPIENT_BLOCKED;
        else if (!this.sender.hasMoney(this.amount))
            this.transactionOutcomes = TransactionOutcomes.SENDER_HAS_NOT_MONEY;
        else {
            this.sender.takeMoney(this.amount);
            this.recipient.addMoney(this.amount);
            this.transactionOutcomes = TransactionOutcomes.SUCCESS;
            System.out.printf("[ТРАНЗАКЦИЯ]: %s -> %s | %d рублей\n", this.sender.getHolder().toString(), this.recipient.getHolder().toString(), this.amount);
            return true;
        }
        return false;
    }

    private static enum TransactionOutcomes {
        SUCCESS,
        SENDER_BLOCKED,
        RECIPIENT_BLOCKED,
        SENDER_HAS_NOT_MONEY
    }
}
