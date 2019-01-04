package nl.sparrow.cashflow.logic.services.mappers;

import nl.sparrow.cashflow.CashFlowApp;
import nl.sparrow.cashflow.logic.models.Account;
import nl.sparrow.cashflow.logic.models.CsvData;
import nl.sparrow.cashflow.logic.services.AccountService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RaboMapper implements Mapper
{
   private static final String ACCOUNT_IBAN = "IBAN/BBAN";
   private static final String TRANSACTION_AMOUNT = "BEDRAG";
   private static final String TRANSACTION_DESCRIPTION = "OMSCHRIJVING-1";
   private static final String TRANSACTION_OTHER = "NAAM TEGENPARTIJ";

   private Map<String, Integer> index = new HashMap<>();

   @Override
   public void map(CsvData csvData)
   {
      this.setIndexes(csvData.getHeader());
      this.mapToTransaction(csvData.getData());
   }

//TODO Header info in list oid function same for every mapper --> part of interface/abstract class
   private void setIndexes(String[] header) {
      System.out.println("start extracting csv header");
      for (int i = 0; i < header.length; i++) {
         String attribute = header[i].toUpperCase();
         index.put(attribute, i);
         System.out.println(i + ". " + attribute);
      }

      //check if expected attributes are in the header
      if (!(index.containsKey(ACCOUNT_IBAN)
         && index.containsKey(TRANSACTION_AMOUNT)
         && index.containsKey(TRANSACTION_DESCRIPTION)
         && index.containsKey(TRANSACTION_OTHER)
      )) {

         System.out.println("Missing header information");
      }

      System.out.println("finish extracting csv header");
   }

   private void mapToTransaction(List<String[]> transactionDataList) {
      System.out.println("start mapping csv data");
      AccountService accountService = CashFlowApp.getAccountService();
      for (String[] transactionData : transactionDataList) {
         String accountIban = transactionData[index.get(ACCOUNT_IBAN)];
         double transactionAmount = Double.parseDouble(transactionData[index.get(TRANSACTION_AMOUNT)]);
         String transactionDescription = transactionData[index.get(TRANSACTION_OTHER)] + " - " + transactionData[index.get(TRANSACTION_DESCRIPTION)];

         Account account = accountService.getAccount(accountIban);
         if (account != null) {
            System.out.println(accountIban + ": " + account);
            account.addTransaction(transactionAmount, transactionDescription);

            System.out.println("Iban, bedrag : " + accountIban + ", " + transactionAmount);
            System.out.println("omschrijving: " + transactionDescription);
         }
      }
      System.out.println("finish mapping csv data");
   }
}
