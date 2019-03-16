package nl.sparrow.cashflow.gui.dataModels;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import nl.sparrow.cashflow.logic.models.Transaction;

import java.time.LocalDate;

public class TransactionModel
{
   private SimpleStringProperty            description;
   private SimpleDoubleProperty            amount;
   private SimpleObjectProperty<LocalDate> date;


   public TransactionModel(Transaction transaction)
   {
      this.amount = new SimpleDoubleProperty(transaction.getAmount());
      this.date = new SimpleObjectProperty<LocalDate>(transaction.getDate());
      this.description = new SimpleStringProperty( (transaction.getNameOther() == null ? "" : (transaction.getNameOther() + " - ")) + transaction.getDescription());
   }


   public String getDescription()
   {
      return description.get();
   }


   public SimpleStringProperty descriptionProperty()
   {
      return description;
   }


   public void setDescription(String description)
   {
      this.description.set(description);
   }


   public double getAmount()
   {
      return amount.get();
   }


   public SimpleDoubleProperty amountProperty()
   {
      return amount;
   }


   public void setAmount(double amount)
   {
      this.amount.set(amount);
   }


   public LocalDate getDate()
   {
      return date.get();
   }


   public SimpleObjectProperty<LocalDate> dateProperty()
   {
      return date;
   }


   public void setDate(LocalDate date)
   {
      this.date.set(date);
   }
}
