package main.models;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private static List<Account> accounts = new ArrayList<>();
    private String name;
    private String iban;
    private List<Transaction> transactions = new ArrayList<>();
    private double balance;

    public Account(String iban, String name) {
        this.iban = iban;
        this.name = name;

        if (!accounts.contains(this)) {
            accounts.add(this);
        }
    }

    public Account(String iban) {
        this(iban, iban);
    }

    public static void removeAccount(Account account) {
        accounts.remove(account);
    }

    public void addTransaction(double amount, Category category) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCategory(category);

        transactions.add(transaction);
    }

    public void addTransaction(double amount) {
        addTransaction(amount, null);
    }

    public static List<Account> getAccounts() {
        return accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIban() {
        return iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            return ((Account) obj).getIban().equals(this.getIban());
        } else {
            return super.equals(obj);
        }
    }
}
