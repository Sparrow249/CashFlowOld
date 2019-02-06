package nl.sparrow.cashflow.logic.models;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Month;

public final class Transaction
{
   private final double    amount;
   private final LocalDate date;
   private final String    ibanOther;
   private final String    nameOther;
   private final String    description;


   private Transaction(Builder builder)
   {
      this.amount = builder.amount;
      this.date = builder.date;
      this.ibanOther = builder.ibanOther;
      this.nameOther = builder.nameOther;
      this.description = builder.description;
   }


   public double getAmount()
   {
      return amount;
   }


   public String getDescription()
   {
      return description;
   }


   public LocalDate getDate()
   {
      return date;
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
      return new StringBuilder("[Transaction]")
         .append(" --amount=" + amount)
         .append(" --date=" + date)
         .append(" --otherIban=" + ibanOther)
         .append(" --othername=" + nameOther)
         .append(" --description=" + description)
         .toString();
   }


   @Override
   public boolean equals(Object obj)
   {
      if (obj instanceof Transaction)
      {
         Transaction other = (Transaction)obj;
         if (date.equals(other.getDate())
            && amount == other.getAmount()
            && ibanOther.equals(other.getIbanOther()))
         {
            return true;
         }
      }
      return false;
   }


   @Override
   public int hashCode()
   {
      return ibanOther.hashCode() + 3 * date.hashCode();
   }


   public static class Builder
   {
      private final double    amount;
      private final LocalDate date;
      private final String    ibanOther;
      private       String    nameOther;
      private       String    description;


      public Builder(double amount, @NotNull LocalDate date, @NotNull String ibanOther)
      {
         this.amount = amount;
         this.date = date;
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
