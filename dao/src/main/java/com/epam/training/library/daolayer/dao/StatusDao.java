package com.epam.training.library.daolayer.dao;

import com.epam.training.library.daolayer.connection.ConnectionManager;
import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDao extends AbstractDao<Status> {
    private static final String FIND_ALL_QUERY = "SELECT id, name FROM status";
    private static final String FIND_ONE_QUERY = "SELECT id, name FROM status WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO status (id, name) VALUES (DEFAULT, ?)";
    private static final String UPDATE_QUERY = "UPDATE status SET name = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM status WHERE id = ?";


    public StatusDao(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    protected String getFindOneQuery() {
        return FIND_ONE_QUERY;
    }

    @Override
    protected String getFindAllQuery() {
        return FIND_ALL_QUERY;
    }

    @Override
    protected String getCreateQuery() {
        return INSERT_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }


    @Override
    protected List<Status> parseResultSet(ResultSet resultSet) throws PersistException {
        List<Status> statusList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Status status = new Status();

                Long id = resultSet.getLong(Status.ID_ALIAS);
                status.setId(id);

                String name = resultSet.getString(Status.NAME_ALIAS);
                status.setName(name);

                statusList.add(status);
            }
        } catch (SQLException e) {
            throw new PersistException("Error within StatusDao parseResultSet(): " + e.getMessage(), e);
        }
        return statusList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Status entity) throws PersistException {
        try {
            String name = entity.getName();
            statement.setString(2, name);

        } catch (SQLException e) {
            throw new PersistException("Error within StatusDao prepareStatementForInsert(): " + e.getMessage(), e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Status entity) throws PersistException {
        try {
            String name = entity.getName();
            statement.setString(1, name);

            Long id = entity.getId();
            statement.setLong(2, id);

        } catch (SQLException e) {
            throw new PersistException("Error within StatusDao prepareStatementForInsert(): " + e.getMessage(), e);
        }
    }
}
