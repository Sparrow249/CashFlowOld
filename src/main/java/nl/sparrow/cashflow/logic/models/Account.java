package nl.sparrow.cashflow.logic.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class Account extends Observable
{
   private final static Logger            LOGGER       = Logger.getLogger(Account.class.getName());
   private final        String            iban;
   private              List<Transaction> transactions = new ArrayList<>();


   public Account(String iban)
   {
      this.iban = iban;
   }


   public void addTransaction(Transaction transaction)
   {
      transactions.add(transaction);

      setChanged();
      notifyObservers();
      LOGGER.fine("Transaction " + transaction.toString() + " added to " + this.iban);
   }


   public List<Transaction> getAllTransactions()
   {
      LOGGER.finer("All transactions requested: " + transactions);
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

      LOGGER.finer("["+this.toString()+"] Transactions requested with " + filter + " requested: " + filteredTransactions);
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
}
