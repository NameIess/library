package com.epam.training.library.daolayer.connection.exception;

import java.io.Serializable;

public class DbConnectionPoolException extends RuntimeException implements Serializable {
    public DbConnectionPoolException() {
    }

    public DbConnectionPoolException(String message) {
        super(message);
    }

    public DbConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbConnectionPoolException(Throwable cause) {
        super(cause);
    }

    public DbConnectionPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
