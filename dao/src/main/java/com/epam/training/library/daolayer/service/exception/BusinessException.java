package com.epam.training.library.daolayer.service.exception;

import java.io.Serializable;

/**
 * General exception based on business logic.
 */
public class BusinessException extends Exception implements Serializable {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
