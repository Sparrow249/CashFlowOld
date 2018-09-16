package main.services;

import main.models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private List<Account> accounts = new ArrayList<>();

    public void createAccount(String iban) {
        Account account = new Account(iban);

        if (!accounts.contains(account)) {
            accounts.add(account);
            System.out.println("Created : " + iban + ", " + account);
        }
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public Account getAccount(String iban) {
        for (Account account : accounts) {
            if (account.getIban().equals(iban)) {
                return account;
            }
        }
        return null;
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }
}
