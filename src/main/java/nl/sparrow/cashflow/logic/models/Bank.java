package nl.sparrow.cashflow.logic.models;

import nl.sparrow.cashflow.logic.services.mappers.RaboCsvDataMapper;
import nl.sparrow.cashflow.logic.services.mappers.CsvDataMapper;

public enum Bank
{
   RABO(new RaboCsvDataMapper());

   private CsvDataMapper mapper;


   Bank(CsvDataMapper mapper)
   {
      this.mapper = mapper;
   }


   public CsvDataMapper getMapper()
   {
      return mapper;
   }
}
