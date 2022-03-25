package ru.matthew_shtyasek.atm_system;

import ru.matthew_shtyasek.atm_system.exception.UserAuthorizationException;

import java.util.ArrayList;
import java.util.Arrays;

public class User {
    private String firstname;
    private String lastname;
    private long uuid;
    private ArrayList<Account> accounts;

    public User(final long uuid, final byte[] pinHash) throws UserAuthorizationException {
        if (!this.validate(uuid, pinHash))
            throw new UserAuthorizationException();
        this.load(uuid);
    }

    private boolean validate(final long uuid, final byte[] pinHash) {
        if (this.canLoad(uuid)) {
            return Arrays.equals(pinHash, this.getPin(uuid));
        }
        return false;
    }

    private boolean canLoad(final long uuid) {
        return true; //todo: normal solution with database
    }

    private byte[] getPin(final long uuid) {
        byte[] pinHash = new byte[255]; //todo: load pin-code from the database
        for (int i = 0; i < pinHash.length; ++i)
            pinHash[i] = (byte)i;
        return pinHash;
    }

    private void load(final long uuid) {
        this.uuid = uuid;
        this.firstname = "Matthew"; //todo: load from the database
        this.lastname = "Giovannini";
        //todo: accounts
    }

    @Override
    public String toString() {
        return String.format("%s %s[%d]", this.firstname, this.lastname, this.uuid);
    }
}
