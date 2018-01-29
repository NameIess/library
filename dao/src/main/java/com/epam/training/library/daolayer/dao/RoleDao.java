package com.epam.training.library.daolayer.dao;

import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends AbstractDao<Role> {
    private static final String FIND_ALL_QUERY = "SELECT id, name FROM role";
    private static final String FIND_ONE_QUERY = "SELECT id, name FROM role WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO role (id, name) VALUES (DEFAULT, ?)";
    private static final String UPDATE_QUERY = "UPDATE role SET name = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM role WHERE id = ?";

    public RoleDao(Connection connection) {
        super(connection);
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
    protected List<Role> parseResultSet(ResultSet resultSet) throws PersistException {
        List<Role> roleList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Role role = new Role();
                Long id = resultSet.getLong(Role.ID_ALIAS);
                role.setId(id);

                String name = resultSet.getString(Role.NAME_ALIAS);
                role.setName(name);

                roleList.add(role);
            }
        } catch (Exception e) {
            throw new PersistException("Error within RoleDao parseResultSet(): " + e.getMessage(), e);
        }
        return roleList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Role entity) throws PersistException {
        try {
            String name = entity.getName();
            statement.setString(1, name);

        } catch (SQLException e) {
            throw new PersistException("Error within RoleDao prepareStatementForInsert(): " + e.getMessage(), e);

        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Role entity) throws PersistException {
        try {
            String name = entity.getName();
            statement.setString(1, name);

            Long id = entity.getId();
            statement.setLong(2, id);

        } catch (SQLException e) {
            throw new PersistException("Error within RoleDao prepareStatementForUpdate(): " + e.getMessage(), e);
        }
    }
}
