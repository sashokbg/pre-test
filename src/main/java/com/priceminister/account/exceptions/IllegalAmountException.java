package com.priceminister.account.exceptions;

public class IllegalAmountException extends AccountException{
    public IllegalAmountException() {
    }

    public IllegalAmountException(String message) {
        super(message);
    }

    public IllegalAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAmountException(Throwable cause) {
        super(cause);
    }

    public IllegalAmountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
