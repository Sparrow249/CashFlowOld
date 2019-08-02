package nl.sparrow.cashflow.logic.exceptions;

public class UnexpectedHeaderException extends TechnicalException {
    public UnexpectedHeaderException(){
        super(ExceptionMessage.UNEXPECTED_HEADER);
    }
}
