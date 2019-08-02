package nl.sparrow.cashflow.logic.exceptions;

import nl.sparrow.cashflow.CashFlowApp;

public class TechnicalException extends RuntimeException {
    public TechnicalException(String message) {
        super(message);
        CashFlowApp.logger.warn(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
        CashFlowApp.logger.warn(message);
    }

    public TechnicalException(Throwable cause) {
        super(cause);
        CashFlowApp.logger.warn(cause.getMessage());
    }
}
