package nl.sparrow.cashflow.logic.exceptions;

public class InvalidIbanException extends RuntimeException
{
   public static final String MESSAGE = "Het opgegeven IBAN heeft niet het juiste format";


   public InvalidIbanException()
   {
      super(MESSAGE);
   }
}
