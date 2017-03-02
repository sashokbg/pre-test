package com.priceminister.account;

public class IllegalBalanceException extends Exception {

    public IllegalBalanceException() {
    }

    public IllegalBalanceException(String message) {
        super(message);
    }

    public IllegalBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalBalanceException(Throwable cause) {
        super(cause);
    }

    public IllegalBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
