package nl.sparrow.cashflow.logic.models;


public class Transaction
{
   private final double amount;
   private final String ibanOther;
   private final String nameOther;
   private final String description;


   private Transaction(Builder builder)
   {
      this.amount = builder.amount;
      this.description = builder.description;
      this.ibanOther = builder.ibanOther;
      this.nameOther = builder.nameOther;
   }


   public double getAmount()
   {
      return amount;
   }


   public String getDescription()
   {
      return description;
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
      return Double.toString(amount);
   }


   public static class Builder
   {
      private final double amount;
      private final String ibanOther;

      private String nameOther;
      private String description;


      public Builder(double amount, String ibanOther)
      {
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
