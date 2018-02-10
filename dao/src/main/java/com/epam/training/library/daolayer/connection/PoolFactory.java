package com.epam.training.library.daolayer.connection;

/**
 * Contains a factory method for database pool connection creation
 */
public interface PoolFactory {
    DbConnectionPool createPool();
}
