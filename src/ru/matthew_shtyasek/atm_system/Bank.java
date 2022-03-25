package ru.matthew_shtyasek.atm_system;

import ru.matthew_shtyasek.atm_system.exception.NotBankExistsException;
import ru.matthew_shtyasek.atm_system.exception.UserAuthorizationException;

import java.util.ArrayList;

public class Bank {
    private String name;
    private long uuid;
    private ArrayList<User> users;

    public Bank(final long uuid) throws NotBankExistsException {
        if (!this.exists(uuid))
            throw new NotBankExistsException();
        this.load(uuid);
    }

    private boolean exists(final long uuid) {
        return true; //todo: checking in database
    }

    private void load(final long uuid) {
        this.name = "Bank";
        this.uuid = uuid;
        this.users = new ArrayList<>();
        long user_uuid = 12345567;
        try {
            this.users.add(new User(user_uuid, new byte[]{1, 23, 4, 5, 6, 7, 8, 9, 0, 100}));
        } catch (UserAuthorizationException e) {
            System.out.printf("User [%d] doesn't exists!\n", user_uuid);
        }
    }
}
