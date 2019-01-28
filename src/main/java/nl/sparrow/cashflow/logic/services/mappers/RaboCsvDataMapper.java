package nl.sparrow.cashflow.logic.services.mappers;

import nl.sparrow.cashflow.logic.exceptions.UnexpectedHeaderException;
import nl.sparrow.cashflow.logic.models.Account;
import nl.sparrow.cashflow.logic.models.CsvData;
import nl.sparrow.cashflow.logic.models.Transaction;
import nl.sparrow.cashflow.logic.services.AccountService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class RaboCsvDataMapper implements CsvDataMapper
{
   private static final String ACCOUNT_IBAN            = "IBAN/BBAN";
   private static final String TRANSACTION_AMOUNT      = "BEDRAG";
   private static final String TRANSACTION_DESCRIPTION = "OMSCHRIJVING-1";
   private static final String TRANSACTION_OTHER       = "NAAM TEGENPARTIJ";
   private static final String TRANSACTION_DATE = "DATUM";

   private static final DateTimeFormatter PATTERN_DATUM = DateTimeFormatter.ofPattern("yyyy-MM-dd");


   @Override
   public void map(CsvData csvData, AccountService accountService)
   {
      checkHeader(csvData.getHeader());

      csvData.getData().stream()
         .forEach(data -> {
            Account account = accountService.getAccount(data.get(ACCOUNT_IBAN));
            if (account != null)
            {
               Transaction transaction = new Transaction();
               transaction.setAmount(Double.parseDouble(data.get(TRANSACTION_AMOUNT)));
               transaction.setDescription(data.get(TRANSACTION_OTHER)+" - "+data.get(TRANSACTION_DESCRIPTION));
               transaction.setDate(LocalDate.parse(data.get(TRANSACTION_DATE), PATTERN_DATUM));

               account.addTransaction(transaction);
            }
         });
   }

   @Override
   public void checkHeader(String[] header)
   {
      String[] fields = {
         ACCOUNT_IBAN,
         TRANSACTION_AMOUNT,
         TRANSACTION_DESCRIPTION,
         TRANSACTION_OTHER,
         TRANSACTION_DATE
      };

      if (!Arrays.asList(header).containsAll(Arrays.asList(fields)))
      {
         throw new UnexpectedHeaderException();
      }
   }


}
