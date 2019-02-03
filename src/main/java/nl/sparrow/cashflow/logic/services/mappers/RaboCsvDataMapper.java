package nl.sparrow.cashflow.logic.services.mappers;

import nl.sparrow.cashflow.logic.exceptions.UnexpectedHeaderException;
import nl.sparrow.cashflow.logic.models.Account;
import nl.sparrow.cashflow.logic.models.CsvData;
import nl.sparrow.cashflow.logic.models.Transaction;
import nl.sparrow.cashflow.logic.services.AccountService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class RaboCsvDataMapper implements CsvDataMapper
{
   private static final String A_IBAN          = "IBAN/BBAN";
   private static final String T_AMOUNT        = "BEDRAG";
   private static final String T_DESCRIPTION_1 = "OMSCHRIJVING-1";
   private static final String T_DESCRIPTION_2 = "OMSCHRIJVING-2";
   private static final String T_DESCRIPTION_3 = "OMSCHRIJVING-3";
   private static final String T_OTHER_IBAN    = "TEGENREKENING IBAN/BBAN";
   private static final String T_OTHER_NAME    = "NAAM TEGENPARTIJ";
   private static final String T_DATE          = "DATUM";

   private static final DateTimeFormatter PATTERN_DATUM = DateTimeFormatter.ofPattern("yyyy-MM-dd");


   @Override
   public void map(CsvData csvData, AccountService accountService)
   {
      checkHeader(csvData.getHeader());

      csvData.getData().stream()
         .forEach(data -> {
            Account account = accountService.getAccount(data.get(A_IBAN));
            if (account != null)
            {
               double amount = Double.parseDouble(data.get(T_AMOUNT));
               String description = data.get(T_DESCRIPTION_1) + " " + data.get(T_DESCRIPTION_2) + " " + data.get(T_DESCRIPTION_3);
               String otherIban = data.get(T_OTHER_IBAN);
               String otherName = data.get(T_OTHER_NAME);
               LocalDate date = LocalDate.parse(data.get(T_DATE), PATTERN_DATUM);
               try
               {
                  Transaction transaction = new Transaction.Builder(date, amount, otherIban).setNameOther(otherName).setDescription(description).build();
                  System.out.println(transaction.toString());
                  account.addTransaction(transaction);
               }
               catch (Exception e)
               {
                  new Exception("Something went wrong building the Transaction", e); //TODO remove
               }
            }
         });
   }


   @Override
   public void checkHeader(String[] header)
   {
      String[] fields = {
         A_IBAN,
         T_AMOUNT,
         T_DESCRIPTION_1,
         T_DESCRIPTION_2,
         T_DESCRIPTION_3,
         T_OTHER_IBAN,
         T_OTHER_NAME,
         T_DATE
      };

      if (!Arrays.asList(header).containsAll(Arrays.asList(fields)))
      {
         throw new UnexpectedHeaderException();
      }
   }


}
