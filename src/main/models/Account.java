package main.models;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Account {
    private String iban;
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String iban) {
        this.iban = iban;
    }

    public void addTransaction(double amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);

        transactions.add(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public List<Transaction> getTransactions(Predicate<Transaction> filter) {
        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (filter.test(transaction)) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }

    public String getIban() {
        return iban;
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
