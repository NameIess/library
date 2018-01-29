package com.epam.training.library.daolayer.dao;

import com.epam.training.library.daolayer.connection.DbConnectionPool;
import com.epam.training.library.daolayer.connection.exception.DbConnectionPoolException;
import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.Identified;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T extends Identified> implements GenericDao<T> {
    private static final Logger Log = LogManager.getLogger(AbstractDao.class.getSimpleName());
    private static final int SINGLE_ROW = 1;
    private Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    protected abstract String getFindOneQuery();

    protected abstract String getFindAllQuery();

    protected abstract String getCreateQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet resultSet) throws PersistException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T entity) throws PersistException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T entity) throws PersistException;

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void create(T entity) throws PersistException {
        String insertQuery = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            prepareStatementForInsert(statement, entity);
            int counter = statement.executeUpdate();
            if (counter != SINGLE_ROW) {
                throw new PersistException("Error within AbstractDao create(): query modifies more than one record. Modified records: " + counter);
            }
        } catch (SQLException e) {
            throw new PersistException("Error within AbstractDao create(): " + e.getMessage(), e);
        }
    }

    @Override
    public T findOne(Long id) throws PersistException {
        String findOne = getFindOneQuery();
        List<T> list;
        try (PreparedStatement statement = connection.prepareStatement(findOne)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new PersistException("Error within AbstractDao findOne(): " + e.getMessage(), e);
        }
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() > SINGLE_ROW) {
            throw new PersistException("Error within AbstractDao findOne(): received more than one record.");
        }
        return list.iterator().next();
    }

    @Override
    public void update(T entity) throws PersistException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(statement, entity);
            int counter = statement.executeUpdate();
            if (counter != SINGLE_ROW) {
                throw new PersistException("Error within AbstractDao update(): query modifies more than one record. Modified records: " + counter);
            }
        } catch (SQLException e) {
            throw new PersistException("Error within AbstractDao update(): " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(T entity) throws PersistException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            Long id = entity.getId();
            statement.setLong(1, id);
            int counter = statement.executeUpdate();
            if (counter != SINGLE_ROW) {
                throw new PersistException("Error within AbstractDao delete(): query modifies more than one record. Modified records: " + counter);
            }
        } catch (SQLException e) {
            throw new PersistException("Error within AbstractDao update(): " + e.getMessage(), e);
        }
    }

    @Override
    public List<T> findAll() throws PersistException {
        List<T> list;
        String sql = getFindAllQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new PersistException("Error within AbstractDao findAll(): " + e.getMessage(), e);
        }
        return list;
    }


    @Override
    public void startTransaction() {
        try {
            connection.setAutoCommit(false);
            Log.info("A transaction has been started");
        } catch (SQLException e) {
            throw new DbConnectionPoolException("Error within AbstractDao startTransaction(). Can not disable autocommit and start transaction: " + e.getMessage(), e);
        }
    }

    @Override
    public void stopTransaction() {
        try {
            connection.setAutoCommit(true);
            Log.info("A transaction has been finished");
        } catch (SQLException e) {
            throw new DbConnectionPoolException("Error within AbstractDao stopTransaction(). Can not enable autocommit and stop transaction: " + e.getMessage(), e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            Log.info("A transaction has been committed");
        } catch (SQLException e) {
            throw new DbConnectionPoolException("Error within AbstractDao commit(). " + e.getMessage(), e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            Log.info("A transaction has been destroyed");
        } catch (SQLException e) {
            throw new DbConnectionPoolException("Error within AbstractDao rollback(). Can not rollback DB com.epam.training.library.daolayer.connection: " + e.getMessage(), e);
        }
    }

    @Override
    public void releaseConnection() {
        if (connection != null) {
            DbConnectionPool connectionPool = DbConnectionPool.getInstance();
            connectionPool.releaseConnection(connection);
        }
    }
}
