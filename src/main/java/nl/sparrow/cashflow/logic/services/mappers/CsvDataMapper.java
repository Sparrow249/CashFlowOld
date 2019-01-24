package nl.sparrow.cashflow.logic.services.mappers;

import nl.sparrow.cashflow.logic.models.CsvData;
import nl.sparrow.cashflow.logic.services.AccountService;

public interface CsvDataMapper
{
   abstract void checkHeader(String[] header);


   abstract void map(CsvData data, AccountService accountService);
}
