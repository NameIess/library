package com.epam.training.library.daolayer.connection;

import com.epam.training.library.daolayer.connection.exception.DbConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DbConnectionPool {
    private static final Logger Log = LogManager.getLogger(DbConnectionPool.class.getSimpleName());
    private static final AtomicBoolean IS_INITIALIZED = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();
    private static DbConnectionPool instance;
    private final String url;
    private final String username;
    private final String password;
    private final int maxConnectionAmount;
    private final String driver;
    private List<Connection> availableConnection;

    private DbConnectionPool(String url, String username, String password, int maxConnectionAmount, String driver) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.maxConnectionAmount = maxConnectionAmount;
        this.driver = driver;
        this.availableConnection = new ArrayList<>();
    }

    public static void createInstance(String url, String username, String password, int maxConnectionAmount, String driver) {
        if (!IS_INITIALIZED.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new DbConnectionPool(url, username, password, maxConnectionAmount, driver);
                    instance.loadDriver();
                    IS_INITIALIZED.set(true);
                    Log.info("Connection pool has been created.");
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void releasePoolAndConnections() {
        if (IS_INITIALIZED.get()) {
            instance.closePoolConnections();
        }
    }

    public static DbConnectionPool getInstance() {
        lock.lock();
        try {
            return instance;
        } finally {
            lock.unlock();
        }
    }

    public Connection getConnection() {
        lock.lock();
        try {
            Connection connection;
            Iterator<Connection> connectionIterator = availableConnection.iterator();
            if (connectionIterator.hasNext()) {
                connection = connectionIterator.next();
                connectionIterator.remove();
                Log.info("Connection " + connection + " has been received from pool.");
            } else {
                connection = createConnection();
            }

            return connection;
        } finally {
            lock.unlock();
        }
    }

    public void releaseConnection(Connection connection) {
        lock.lock();
        if (connection == null) {
            throw new DbConnectionPoolException("Error while closing the connection. Can't close the connection for pool: connection equal null.");
        }
        try {
            int connectionsAmount = availableConnection.size();
            if (connectionsAmount < maxConnectionAmount) {
                availableConnection.add(connection);
                Log.info("A connection" + connection + " has been released back to pool. Current Pool size: = " + availableConnection.size());
            } else {
                connection.close();
                Log.info("A connection has been closed. Current Pool size: = " + availableConnection.size());
            }
        } catch (SQLException e) {
            throw new DbConnectionPoolException("Error while closing the connection. Can't close the connection for pool. " + e.getMessage(), e);
        } finally {
            lock.unlock();
        }
    }

    private Connection createConnection() {
        lock.lock();
        try {
            Connection connection;
            try {
                if (username == null) {
                    connection = DriverManager.getConnection(url);
                } else {
                    connection = DriverManager.getConnection(url, username, password);
                }
            } catch (SQLException e) {
                throw new DbConnectionPoolException("Error while creating connection. " + e.getMessage(), e);
            }
            Log.debug("Connection has been created: " + connection);
            return connection;
        } finally {
            lock.unlock();
        }
    }

    private void loadDriver() {
        lock.lock();
        try {
            Driver connectionDriver = (Driver) Class.forName(driver).newInstance();
            DriverManager.registerDriver(connectionDriver);

            Log.info("A JDBC driver has been registered");
        } catch (Exception e) {
            throw new DbConnectionPoolException("Can't register the JDBC driver. " + e.getMessage(), e);
        } finally {
            lock.unlock();
        }
    }

    private void closePoolConnections() {
        lock.lock();
        try {
            Iterator<Connection> connectionIterator = availableConnection.iterator();
            while (connectionIterator.hasNext()) {
                try {
                    Connection connection = connectionIterator.next();
                    connection.close();
                    connectionIterator.remove();
                } catch (SQLException e) {
                    throw new DbConnectionPoolException("Error while closing the connection. Can't close the connection for pool. " + e.getMessage(), e);
                }
            }
            Log.info("All connections have been closed. Current Pool size: = " + availableConnection.size());
        } finally {
            lock.unlock();
        }
    }
}
