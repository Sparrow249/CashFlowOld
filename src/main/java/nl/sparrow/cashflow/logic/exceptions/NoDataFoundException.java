package nl.sparrow.cashflow.logic.exceptions;

public class NoDataFoundException extends TechnicalException {
    public NoDataFoundException(){
        super(ExceptionMessage.NO_DATA_FOUND);
    }
}
