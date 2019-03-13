package nl.sparrow.cashflow.logic.services.mappers;

import nl.sparrow.cashflow.logic.models.CsvData;
import nl.sparrow.cashflow.logic.services.AccountService;

public interface CsvDataMapper
{
   /**
    * Check header if it contains all necessary fields
    * @param header data header
    */
   abstract void checkHeader(String[] header);


   /**
    * map csvData to the accounts in accountservice where the data belongs to
    * ! should checkHeader
    * @param data csv imported data
    * @param accountService accountservice with accounts
    */
   abstract void map(CsvData data, AccountService accountService);
}
