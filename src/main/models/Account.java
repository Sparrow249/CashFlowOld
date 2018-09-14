package main.models;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Account {
    private String name;
    private String iban;
    private List<Transaction> transactions = new ArrayList<>();
    private double balance;

    public Account(String iban, String name) {
        this.iban = iban;
        this.name = name;

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

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public List<Transaction> getTransactions(Predicate<Transaction> filter){
        List<Transaction> filteredTransactions = new ArrayList<>();

        for(Transaction transaction: transactions){
            if(filter.test(transaction)){
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
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
