package nl.sparrow.cashflow.logic.exceptions;

public class UnexpectedHeaderException extends RuntimeException {
    public UnexpectedHeaderException(){
        super("De CSV header wijkt af van de template");
    }
}
