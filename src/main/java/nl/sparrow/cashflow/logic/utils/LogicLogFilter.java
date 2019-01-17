package nl.sparrow.cashflow.logic.utils;

import nl.sparrow.cashflow.CashFlowApp;
import nl.sparrow.cashflow.logic.services.AccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class LogicLogFilter implements Filter
{
   @Override
   public boolean isLoggable(LogRecord record)
   {
      List<String> logicLoggers = new ArrayList<>();
      logicLoggers.add(CashFlowApp.class.getName());
      logicLoggers.add(AccountService.class.getName());

      for(String loggerName: logicLoggers){
         if(loggerName.equals(record.getLoggerName())){
            return true;
         }
      }
      return false;
   }
}
