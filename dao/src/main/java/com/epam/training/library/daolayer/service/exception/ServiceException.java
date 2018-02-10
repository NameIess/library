package com.epam.training.library.daolayer.service.exception;

import java.io.Serializable;

/**
 * General exception of the Service layer.
 */
public class ServiceException extends Exception implements Serializable {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
