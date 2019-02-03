package nl.sparrow.cashflow.logic.models;

import java.time.LocalDate;
import java.time.Month;

public class Transaction
{
   private final double    amount;
   private final String    ibanOther;
   private final String    nameOther;
   private final String    description;
   private final LocalDate date;


   private Transaction(Builder builder)
   {
      this.amount = builder.amount;
      this.description = builder.description;
      this.ibanOther = builder.ibanOther;
      this.nameOther = builder.nameOther;
      this.date = builder.date;
   }


   public double getAmount()
   {
      return amount;
   }


   public String getDescription()
   {
      return description;
   }


   public String getDate()
   {
      return date.toString();
   }


   public int getDateYear()
   {
      return date.getYear();
   }


   public Month getDateMonth()
   {
      return date.getMonth();
   }


   public int getDateDay()
   {
      return date.getDayOfMonth();
   }


   public String getIbanOther()
   {
      return ibanOther;
   }


   public String getNameOther()
   {
      return nameOther;
   }


   @Override
   public String toString()
   {
      return "[Transaction] Datum = " + date + ", Bedrag = " + amount + ", Beschrijving = " + nameOther + " - " + description + ", Tegenrekening = "
         + ibanOther;
   }


   public static class Builder
   {
      private final double    amount;
      private final String    ibanOther;
      private final LocalDate date;

      private String nameOther;
      private String description;


      public Builder(LocalDate date, double amount, String ibanOther)
      {
         this.date = date;
         this.amount = amount;
         this.ibanOther = ibanOther;
      }


      public Builder setNameOther(String name)
      {
         this.nameOther = name;
         return this;
      }


      public Builder setDescription(String description)
      {
         this.description = description;
         return this;
      }


      public Transaction build()
      {
         return new Transaction(this);
      }
   }
}
