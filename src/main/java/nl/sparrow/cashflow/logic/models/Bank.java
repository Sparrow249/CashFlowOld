package nl.sparrow.cashflow.logic.models;

import nl.sparrow.cashflow.logic.services.mappers.Mapper;
import nl.sparrow.cashflow.logic.services.mappers.RaboMapper;

public enum Bank
{
   RABO(new RaboMapper());

   private Mapper mapper;

   Bank(Mapper mapper){
      this.mapper = mapper;
   }


   public Mapper getMapper()
   {
      return mapper;
   }
}
