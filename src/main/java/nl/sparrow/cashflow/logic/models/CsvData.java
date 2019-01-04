package nl.sparrow.cashflow.logic.models;

import java.util.List;

public class CsvData
{
   private final String[]       header;
   private final List<String[]> data;


   public CsvData(String[] header, List<String[]> data)
   {
      this.header = header;
      this.data = data;
   }


   public String[] getHeader()
   {
      return header;
   }


   public List<String[]> getData()
   {
      return data;
   }
}
