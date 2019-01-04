package nl.sparrow.cashflow.logic.services;

import nl.sparrow.cashflow.logic.models.Bank;
import nl.sparrow.cashflow.logic.models.CsvData;
import nl.sparrow.cashflow.logic.services.mappers.Mapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvUploadService
{
   private static final String LINE_SEPERATOR = "\",\"";

   public void uploadFile(File csvFile, Bank bank) {
      String line;
      String[] csvTransaction;
      List<String[]> csvTransactions = new ArrayList<>();

      try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
         while ((line = reader.readLine()) != null) {
            csvTransaction = line.split(LINE_SEPERATOR);
            for (int i = 0; i < csvTransaction.length; i++) {
               csvTransaction[i] = csvTransaction[i].replace(",", ".");
            }
            csvTransactions.add(csvTransaction);
         }
      } catch (IOException exception) {
         exception.getStackTrace();
      }

      CsvData csvData = new CsvData(csvTransactions.remove(0), csvTransactions);
      Mapper mapper = bank.getMapper();
      mapper.map(csvData);
   }
}
