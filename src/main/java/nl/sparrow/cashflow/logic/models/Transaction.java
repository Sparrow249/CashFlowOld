package nl.sparrow.cashflow.logic.models;


import java.time.LocalDate;

public class Transaction
{
   private double    amount;
   private String    description;
   private LocalDate date;


   public double getAmount()
   {
      return amount;
   }


   public void setAmount(double amount)
   {
      this.amount = amount;
   }


   public String getDescription()
   {
      return description;
   }


   public void setDescription(String description)
   {
      this.description = description;
   }


   public LocalDate getDate()
   {
      return date;
   }


   public void setDate(LocalDate date)
   {
      this.date = date;
   }


   @Override
   public String toString()
   {
      return "[Transaction] Datum = " + date + ", Bedrag = " + amount + ", Beschrijving = " + description;
   }
}
