package main.models;


public class Transaction {
    private double amount;

    Transaction(){}

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return Double.toString(amount);
    }
}
