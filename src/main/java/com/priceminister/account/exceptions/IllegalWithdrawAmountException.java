package com.priceminister.account.exceptions;

public class IllegalWithdrawAmountException extends Exception{
    public IllegalWithdrawAmountException() {
    }

    public IllegalWithdrawAmountException(String message) {
        super(message);
    }

    public IllegalWithdrawAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalWithdrawAmountException(Throwable cause) {
        super(cause);
    }

    public IllegalWithdrawAmountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
