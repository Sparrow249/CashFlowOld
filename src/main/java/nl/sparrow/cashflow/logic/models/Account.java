package nl.sparrow.cashflow.logic.models;

import nl.sparrow.cashflow.CashFlowApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.function.Predicate;

public class Account extends Observable
{
   private final String            iban;
   private       List<Transaction> transactions = new ArrayList<>();


   public Account(String iban)
   {
      this.iban = iban;
   }


   public void addTransaction(Transaction transaction)
   {
      transactions.add(transaction);

      setChanged();
      notifyObservers();
      CashFlowApp.LOGGER.fine("Transaction added to " + this + ": " + transaction);
   }


   public List<Transaction> getAllTransactions()
   {
      CashFlowApp.LOGGER.finer("All transactions requested for:" + this);
      return transactions;
   }


   public List<Transaction> getTransactions(Predicate<Transaction> filter)
   {
      List<Transaction> filteredTransactions = new ArrayList<>();

      for (Transaction transaction : transactions)
      {
         if (filter.test(transaction))
         {
            filteredTransactions.add(transaction);
         }
      }

      CashFlowApp.LOGGER.finer("Filtered transactions requested for: " + this);
      return filteredTransactions;
   }


   public String getIban()
   {
      return iban;
   }


   @Override
   public boolean equals(Object obj)
   {
      if (obj instanceof Account)
      {
         return ((Account)obj).getIban().equals(this.getIban());
      }
      else
      {
         return super.equals(obj);
      }
   }


   @Override
   public int hashCode()
   {
      return iban.hashCode();
   }


   @Override
   public String toString()
   {
      return new StringBuilder("[Account]")
         .append(" iban=" + iban)
         .toString();
   }
}
