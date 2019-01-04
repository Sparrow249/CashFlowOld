package nl.sparrow.cashflow.gui.dataModels;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import nl.sparrow.cashflow.logic.models.Transaction;

public class TransactionModel {
    private SimpleStringProperty description;
    private SimpleDoubleProperty amount;

    public TransactionModel(Transaction transaction) {
        this.description = new SimpleStringProperty(transaction.getDescription());
        this.amount = new SimpleDoubleProperty(transaction.getAmount());
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }
}
