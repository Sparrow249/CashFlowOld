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

      Transaction t = createTransaction(LocalDate.of(2019, 1, 1),100.00, "NL99TEST9876543210", "test");
      TEST_ACCOUNT.addTransaction(t);
      t = createTransaction(LocalDate.of(2019, 1, 2),12.34, "NL99TEST9876543210", "test");
      TEST_ACCOUNT.addTransaction(t);
      t = createTransaction(LocalDate.of(2018, 12, 1),43.21, "NL99TEST9876543210", "test");
      TEST_ACCOUNT.addTransaction(t);

   }

   private static Transaction createTransaction(LocalDate date, Double amount, String otherIban, String description){
      Transaction transaction = new Transaction.Builder(amount, date, otherIban).setDescription(description).build();

      return transaction;
   }
}
