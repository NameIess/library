package com.epam.training.library.daolayer.connection;

import com.epam.training.library.daolayer.connection.exception.DbConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {
    private static final Logger Log = LogManager.getLogger(ConnectionManager.class.getSimpleName());
    private DbConnectionPool dbConnectionPool;
    private Connection connection;

    public ConnectionManager(DbConnectionPool dbConnectionPool) {
        this.dbConnectionPool = dbConnectionPool;
    }

    private Connection getConnection() {
        if (connection == null) {
            connection = dbConnectionPool.getConnection();
        }
        return connection;
    }

    public void enableAutoCommit() {
        try {
            getConnection().setAutoCommit(true);
            Log.info("Auto commit mode has been switched on for connection: " + getConnection());
        } catch (SQLException e) {
            throw new DbConnectionPoolException("Error within AbstractDao enableAutoCommit(). Can not enable autocommit: " + e.getMessage(), e);
        }
    }

    public PreparedStatement prepareStatement(String sqlQuery) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery);
        return preparedStatement;
    }

    public void startTransaction() {
        try {
            getConnection().setAutoCommit(false);
            Log.info("A transaction has been started. Auto commit mode has been switched off");
        } catch (SQLException e) {
            throw new DbConnectionPoolException("Error within AbstractDao startTransaction(). Can not disable autocommit and start transaction: " + e.getMessage(), e);
        }
    }



    public void commitTransaction() {
        try {
            getConnection().commit();
            Log.info("A transaction has been committed.");
        } catch (SQLException e) {
            throw new DbConnectionPoolException("Error within AbstractDao commitTransaction(). " + e.getMessage(), e);
        }
    }

    public void rollbackTransaction() {
        try {
            getConnection().rollback();
            Log.info("A transaction has been destroyed");
            enableAutoCommit();
        } catch (SQLException e) {
            throw new DbConnectionPoolException("Error within AbstractDao rollbackTransaction(). Can not rollbackTransaction DB com.epam.training.library.daolayer.connection: " + e.getMessage(), e);
        }
    }

    public void releaseConnection() {
        dbConnectionPool.releaseConnection(connection);
        this.connection = null;
    }


}
