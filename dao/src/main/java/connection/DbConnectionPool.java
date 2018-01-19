package connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DbConnectionPool {
    private static final Logger Log = LogManager.getLogger(DbConnectionPool.class.getSimpleName());
    private static AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();
    private static DbConnectionPool instance;
    private List<Connection> availableConnections = new ArrayList<>();
    private String url;
    private String username;
    private String password;
    private int maxConnectionAmount;
    private String driver;

    private DbConnectionPool(String url, String username, String password, int maxConnectionAmount, String driver) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.maxConnectionAmount = maxConnectionAmount;
        this.driver = driver;

        loadDrivers();
    }

    public static void createInstance(String url, String username, String password, int maxConnectionAmount, String driver) {
        if (!isInitialized.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new DbConnectionPool(url, username, password, maxConnectionAmount, driver);
                    isInitialized.set(true);
                }

            } finally {
                lock.unlock();
            }
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

    private void loadDrivers() {
        try {
            Driver connectionDriver = (Driver) Class.forName(driver).newInstance();
            DriverManager.registerDriver(connectionDriver);

            Log.info("A JDBC driver has been registered");
        } catch (Exception e) {
            Log.info("Can't register JDBC driver");
        }
    }

    public Connection getConnection() {
        lock.lock();
        try {
            Connection connection;
            int connectionsAmount = availableConnections.size();
            if (!availableConnections.isEmpty()) {
                connection = availableConnections.get(connectionsAmount - 1);
                availableConnections.remove(connection);

                try {
                    if (connection.isClosed()) {
                        connection = getConnection();
                    }
                } catch (SQLException e) {
                    Log.error("Error while receiving connection.");
                    connection = getConnection();
                }
            } else {
                connection = createConnection();
            }
            return connection;

        } finally {
            lock.unlock();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            if (username == null) {
                connection = DriverManager.getConnection(url);
            } else {
                connection = DriverManager.getConnection(url, username, password);
            }

        } catch (SQLException e) {
            Log.error("Error while creating connection.");
        }

        return connection;
    }

    public void releaseConnection(Connection connection) {
        lock.lock();
        try {
            int connectionsAmount = availableConnections.size();
            if (connection != null && connectionsAmount <= maxConnectionAmount) {
                availableConnections.add(connection);
                Log.info("A connection" + connection + " has been released back to pool.");
            }
        } finally {
            lock.unlock();
        }
    }

    public void releasePool() {
        lock.lock();
        try {
            for (Connection connection : availableConnections) {
                try {
                    connection.close();
                    Log.info("All connections has been closed.");
                } catch (SQLException e) {
                    Log.error("Error while closing connection. Can't close connection for pool.");
                }
            }
            availableConnections.clear();

        } finally {
            lock.unlock();
        }
    }
}
