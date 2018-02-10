package com.epam.training.library.daolayer.connection;

import com.epam.training.library.daolayer.connection.exception.DbConnectionPoolException;
import com.epam.training.library.daolayer.util.DataReader;

import java.io.IOException;
import java.util.Properties;

public class DbConnectionPoolFactory implements PoolFactory {
    private static final String DATABASE_RESOURCE_FILE_NAME = "database.properties";
    private static final String JDBC_URL = "jdbc.url";
    private static final String JDBC_USERNAME = "jdbc.username";
    private static final String JDBC_PASSWORD = "jdbc.password";
    private static final String MAX_CONNECTIONS_AMOUNT = "jdbc.maxActive";
    private static final String DRIVER = "db.driver";

    @Override
    public DbConnectionPool createPool() {
        try {
            DataReader reader = new DataReader();
            Properties resources = reader.parse(DATABASE_RESOURCE_FILE_NAME);
            String jdbcUrl = resources.getProperty(JDBC_URL);
            String jdbcUsername = resources.getProperty(JDBC_USERNAME);
            String jdbcPassword = resources.getProperty(JDBC_PASSWORD);
            String maxConnectionsAmount = resources.getProperty(MAX_CONNECTIONS_AMOUNT);
            int maxActiveConnectionsAmount = Integer.valueOf(maxConnectionsAmount);
            String databaseDriver = resources.getProperty(DRIVER);
            DbConnectionPool.createInstance(jdbcUrl, jdbcUsername, jdbcPassword, maxActiveConnectionsAmount, databaseDriver);
            DbConnectionPool instance = DbConnectionPool.getInstance();

            return instance;
        } catch (IOException e) {
            throw new DbConnectionPoolException("Trouble in DbConnectionPoolFactory within createPool(): " + e.getMessage(), e);
        }
    }
}
