package nl.sparrow.cashflow.gui.menu;

import nl.sparrow.cashflow.logic.models.Account;

import java.time.Month;

public final class AccountMenuSelection
{
   private final Account account;
   private final Integer year;
   private final Month   month;


   AccountMenuSelection(Account account, Integer year, Month month)
   {
      this.account = account;
      this.year = year;
      this.month = month;
   }


   public Account getAccount()
   {
      return account;
   }


   public Integer getYear()
   {
      return year;
   }


   public Month getMonth()
   {
      return month;
   }


   @Override
   public String toString()
   {
      return new StringBuilder("[AccountMenuSelection]")
         .append(" account=" + account)
         .append(" year=" + year)
         .append(" month=" + month)
         .toString();
   }
}
