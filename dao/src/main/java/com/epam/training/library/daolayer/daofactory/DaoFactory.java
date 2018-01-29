package com.epam.training.library.daolayer.daofactory;

import com.epam.training.library.daolayer.connection.DbConnectionPool;
import com.epam.training.library.daolayer.dao.*;
import com.epam.training.library.daolayer.model.*;
import com.epam.training.library.daolayer.util.DataReader;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DaoFactory implements Connectable {
    private static final String DATABASE_RESOURCE_FILE_NAME = "database.properties";
    private static final String JDBC_URL = "jdbc.url";
    private static final String JDBC_USERNAME = "jdbc.username";
    private static final String JDBC_PASSWORD = "jdbc.password";
    private static final String MAX_CONNECTIONS_AMOUNT = "jdbc.maxActive";
    private static final String DRIVER = "db.driver";
    private Map<Class<? extends Identified>, DaoBuilder> creators;
    private DbConnectionPool connectionPool;


    public DaoFactory() {
        creators = new HashMap<>();
        creators.put(Book.class, new DaoBuilder() {
            @Override
            public BookDao create(Connection connection) {
                return new BookDao(connection);
            }
        });
        creators.put(Receipt.class, new DaoBuilder() {
            @Override
            public ReceiptDao create(Connection connection) {
                return new ReceiptDao(connection);
            }
        });
        creators.put(Role.class, new DaoBuilder() {
            @Override
            public RoleDao create(Connection connection) {
                return new RoleDao(connection);
            }
        });
        creators.put(Status.class, new DaoBuilder() {
            @Override
            public StatusDao create(Connection connection) {
                return new StatusDao(connection);
            }
        });
        creators.put(User.class, new DaoBuilder() {
            @Override
            public UserDao create(Connection connection) {
                return new UserDao(connection);
            }
        });
    }

    private void initDatabasePool()  {
        DataReader reader = new DataReader();
        Properties resources = reader.parse(DATABASE_RESOURCE_FILE_NAME);
        String jdbcUrl = resources.getProperty(JDBC_URL);
        String jdbcUsername = resources.getProperty(JDBC_USERNAME);
        String jdbcPassword = resources.getProperty(JDBC_PASSWORD);
        String maxConnectionsAmount = resources.getProperty(MAX_CONNECTIONS_AMOUNT);
        int maxActiveConnectionsAmount = Integer.valueOf(maxConnectionsAmount);
        String databaseDriver = resources.getProperty(DRIVER);
        DbConnectionPool.createInstance(jdbcUrl, jdbcUsername, jdbcPassword, maxActiveConnectionsAmount, databaseDriver);
        connectionPool = DbConnectionPool.getInstance();
    }

    @Override
    public Connection getConnection() {
        if (connectionPool == null) {
            initDatabasePool();
        }
        Connection connection = connectionPool.getConnection();

        return connection;
    }

    @Override
    public <T extends Identified> GenericDao<T> getDao(Class<T> dtoClass)  {
        DaoBuilder creator = creators.get(dtoClass);
        GenericDao dao = creator.create(getConnection());
        return dao;

    }
}
