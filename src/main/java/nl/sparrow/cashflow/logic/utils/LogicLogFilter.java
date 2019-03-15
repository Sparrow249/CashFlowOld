package nl.sparrow.cashflow.logic.utils;

import nl.sparrow.cashflow.CashFlowApp;
import nl.sparrow.cashflow.logic.services.AccountService;
import nl.sparrow.cashflow.logic.services.CsvUploadService;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class LogicLogFilter implements Filter
{
   @Override
   public boolean isLoggable(LogRecord record)
   {
      String[] logicLoggers = {
         CashFlowApp.class.getName(),
      };


      for (String loggerName : logicLoggers)
      {
         if (loggerName.equals(record.getLoggerName()))
         {
            return true;
         }
      }
      return false;
   }
}
