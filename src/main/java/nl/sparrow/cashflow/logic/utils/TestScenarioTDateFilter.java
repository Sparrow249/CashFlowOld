package nl.sparrow.cashflow.logic.utils;

import nl.sparrow.cashflow.logic.models.Account;
import nl.sparrow.cashflow.logic.models.Transaction;
import nl.sparrow.cashflow.logic.services.AccountService;

import java.time.LocalDate;

public class TestScenarioTDateFilter
{
   public static void setup(AccountService accountService){
      final String TEST_IBAN = "NL00TEST0123456789";
      accountService.addAccount(TEST_IBAN);
      final Account TEST_ACCOUNT = accountService.getAccount(TEST_IBAN);

      Transaction t = createTransaction(100.00, "test", LocalDate.of(2019, 1, 1));
      TEST_ACCOUNT.addTransaction(t);
      t = createTransaction(12.34, "test", LocalDate.of(2019,1,2));
      TEST_ACCOUNT.addTransaction(t);
      t = createTransaction(43.21, "test", LocalDate.of(2018,12,1));
      TEST_ACCOUNT.addTransaction(t);

   }

   private static Transaction createTransaction(Double amount, String description, LocalDate date){
      Transaction transaction = new Transaction();

      transaction.setAmount(amount);
      transaction.setDescription(description);
      transaction.setDate(date);

      return transaction;
   }
}
