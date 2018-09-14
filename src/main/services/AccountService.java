package main.services;

import main.models.Account;
import main.models.Category;
import main.models.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AccountService {
    private List<Account> accounts = new ArrayList<>();

    public void createAccount(String iban, String name) {
        Account account = new Account(iban, name);

        if (!accounts.contains(account)) {
            accounts.add(account);
        }
    }

    public void createAccount(String iban){
        this.createAccount(iban, iban);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public Account getAccount(String iban) {
        for(Account account: accounts){
            if(account.getIban().equals(iban)){
                return account;
            }
        }
        return null;
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }

    public String getName(Account account) {
        return account.getName();
    }

    public void setName(Account account, String name) {
        account.setName(name);
    }

    public String getIban(Account account) {
        return account.getIban();
    }

    public double getBalance(Account account){
        return account.getBalance();
    }

    public void setBalance(Account account, double amount){
        account.setBalance(amount);
    }

    public void addTransaction(Account account, double amount) {
        account.addTransaction(amount;
    }

    public void addTransaction(Account account, double amount, Category category) {
        account.addTransaction(amount, category);
    }

    public List<Transaction> getAllTransactions(Account account) {
        return account.getAllTransactions();
    }

    public List<Transaction> getTransactions(Account account, Predicate<Transaction> filter){
        return account.getTransactions(filter);
    }
}
