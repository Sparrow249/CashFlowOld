package nl.sparrow.cashflow.logic.exceptions;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(){
        super("Er kon geen data gevonden worden");
    }
}
