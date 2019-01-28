package nl.sparrow.cashflow.logic.models;

import java.util.List;
import java.util.Map;

public class CsvData
{
   private String[] header;
   private List<Map<String, String>>       data;


   public String[] getHeader()
   {
      return header;
   }


   public void setHeader(String[] header)
   {
      this.header = header;
   }


   public List<Map<String, String>> getData()
   {
      return data;
   }


   public void setData(List<Map<String, String>> data)
   {
      this.data = data;
   }
}
