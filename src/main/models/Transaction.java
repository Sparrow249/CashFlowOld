package main.models;


public class Transaction {
    private double amount;
    private Category category;

    Transaction(){}

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return Double.toString(amount);
    }
}
