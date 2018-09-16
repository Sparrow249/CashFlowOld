package main.services;

import main.exceptions.InvalidIbanException;
import main.models.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AccountService extends Observable {
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(String iban) {
        iban = iban.toUpperCase();

        if(isValidIban(iban)) {
            Account account = new Account(iban);

            if (!accounts.contains(account)) {
                accounts.add(account);
                //TODO notifyObservers werkt nog niet
                notifyObservers();
                System.out.println("Created : " + iban + ", " + account);
            }
        }
        else{
            throw new InvalidIbanException();
        }


    }

    public void removeAccount(Account account) {
        accounts.remove(account);

        notifyObservers();
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

    private boolean isValidIban(String iban){
        String regexIban = "^[a-zA-Z]{2}[0-9]{2}[a-zA-Z]{4}[0-9]{10}$";
        return iban.matches(regexIban);
    }

    @Override
    public void addObserver(Observer o){
        System.out.println("Observer added to AS: " + o);
        super.addObserver(o);
        System.out.println("AS has "+this.countObservers()+" Observer");
    }
}
