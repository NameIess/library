package com.epam.training.library.daolayer.dao;

import com.epam.training.library.daolayer.connection.ConnectionManager;
import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.Identified;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T extends Identified> implements GenericDao<T> {
    private static final int SINGLE_ROW = 1;
    private ConnectionManager connectionManager;

    public AbstractDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    protected abstract String getFindOneQuery();

    protected abstract String getFindAllQuery();

    protected abstract String getCreateQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet resultSet) throws PersistException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T entity) throws PersistException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T entity) throws PersistException;

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    @Override
    public void create(T entity) throws PersistException {
        String insertQuery = getCreateQuery();
        try (PreparedStatement statement = getConnectionManager().prepareStatement(insertQuery)) {
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
        String findOneQuery = getFindOneQuery();
        List<T> list;
        try (PreparedStatement statement = getConnectionManager().prepareStatement(findOneQuery)) {
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
            throw new PersistException("Error within AbstractDao findOne(): received more than one record. Current records amount: " + list.size());
        }
        return list.iterator().next();
    }

    @Override
    public void update(T entity) throws PersistException {
        String updateQuery = getUpdateQuery();
        try (PreparedStatement statement = getConnectionManager().prepareStatement(updateQuery)) {
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
    public void delete(Long id) throws PersistException {
        String deleteQuery = getDeleteQuery();
        try (PreparedStatement statement = getConnectionManager().prepareStatement(deleteQuery)) {
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
        String findAllQuery = getFindAllQuery();
        try (PreparedStatement statement = getConnectionManager().prepareStatement(findAllQuery)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new PersistException("Error within AbstractDao findAll(): " + e.getMessage(), e);
        }
        return list;
    }
}
