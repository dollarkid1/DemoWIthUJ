package com.example.demowithuj.exceptions;

public class DemoWithUJException extends Exception{
    public DemoWithUJException() {
        super();
    }

    public DemoWithUJException(String message) {
        super(message);
    }

    public DemoWithUJException(String message, Throwable cause) {
        super(message, cause);
    }

    public DemoWithUJException(Throwable cause) {
        super(cause);
    }

    protected DemoWithUJException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
