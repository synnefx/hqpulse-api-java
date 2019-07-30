package com.hqpulse.helper.exceptions;

/**
 * @author Josekutty
 * 30-07-2019
 */
public class RequestValidationError extends Exception {

    public RequestValidationError() {
        super();
    }

    public RequestValidationError(String err) {
        super(err);
    }
}

