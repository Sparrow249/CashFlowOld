package nl.sparrow.cashflow.logic.services;

import nl.sparrow.cashflow.logic.exceptions.NoDataFoundException;
import nl.sparrow.cashflow.logic.models.Bank;
import nl.sparrow.cashflow.logic.models.CsvData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CsvUploadService
{
   private final static Logger LOGGER = Logger.getLogger(CsvUploadService.class.getName());

   private static final String LINE_SEPERATOR = "\",\"";

   private final Bank BANK;


   public CsvUploadService(Bank bank)
   {
      this.BANK = bank;
   }


   public void upload(File csvFile, AccountService accountService)
   {
      LOGGER.finer("Uploading " + csvFile.getPath());
      CsvData transactionData = readData(csvFile);

      if (transactionData.getData().isEmpty())
      {
         throw new NoDataFoundException();
      }
      BANK.getMapper().map(transactionData, accountService);
   }


   private CsvData readData(File csvFile)
   {
      CsvData csvData = new CsvData();
      List<Map<String, String>> dataList = new ArrayList<>();

      try (BufferedReader reader = new BufferedReader(new FileReader(csvFile)))
      {
         try
         {
            String[] header = reader.readLine().split(LINE_SEPERATOR);
            header = Arrays.stream(header).map(attr -> attr.toUpperCase().replace("\"", "")).toArray(String[]::new);
//            for (int i = 0; i < header.length; i++)
//            {
//               header[i] = header[i].toUpperCase().replace("\"", "");
//            }
            csvData.setHeader(header);
         }
         catch (NullPointerException e)
         {
            throw new NoDataFoundException();
         }

         reader.lines()
            .map(line -> line.split(LINE_SEPERATOR))
            .map(data -> Arrays.stream(data).map(value -> value.replace("\"", "").replace(',', '.')).toArray(String[]::new))
            .map(data -> createDataMap(csvData.getHeader(), data))
            .forEach(dataMap -> dataList.add(dataMap));

         csvData.setData(dataList);
      }
      catch (IOException exception)
      {
         LOGGER.warning("Unable to read File");
         exception.getStackTrace();//TODO new exception?
      }

      return csvData;
   }


   private Map<String, String> createDataMap(String[] header, String[] data)
   {
      Map<String, String> dataMap = new HashMap<>();
      if (header.length != data.length)
      {
         LOGGER.warning("Data komt niet overeen met de header");
         throw new RuntimeException("Header and data are incompatible"); //TODO new exception
      }
      else
      {
         for (int i = 0; i < header.length; i++)
         {
            dataMap.put(header[i], data[i]);
         }
      }

      return dataMap;
   }
}
