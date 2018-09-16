package main.exceptions;

public class InvalidIbanException extends RuntimeException {
    public InvalidIbanException(){
        super("Het opgegeven IBAN heeft niet het juiste format");
    }
}
