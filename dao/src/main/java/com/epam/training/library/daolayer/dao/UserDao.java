package com.epam.training.library.daolayer.dao;

import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.Role;
import com.epam.training.library.daolayer.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {
    private static final int SINGLE_ROW = 1;
    private static final String FIND_ONE_BY_EMAIL_PASS = "SELECT u.id, u.role_id, r.name AS 'rolename', u.employee_number, u.name, u.second_name, u.surname, u.email, u.phone_number, u.passport_series, u.passport_number, u.password" +
            " FROM user AS u " +
            " INNER JOIN role AS r ON u.role_id = r.id" +
            " WHERE u.email = ? AND u.password = ?";
    private static final String FIND_ONE_BY_EMAIL = "SELECT u.id, u.role_id, r.name AS 'rolename', u.employee_number, u.name, u.second_name, u.surname, u.email, u.phone_number, u.passport_series, u.passport_number, u.password" +
            " FROM user AS u " +
            " INNER JOIN role AS r ON u.role_id = r.id" +
            " WHERE u.email = ?";
    private static final String FIND_ONE_QUERY = "SELECT u.id, u.role_id, r.name AS 'rolename', u.employee_number, u.name, u.second_name, u.surname, u.email, u.phone_number, u.passport_series, u.passport_number, u.password FROM user AS u INNER JOIN role AS r ON u.role_id = r.id WHERE u.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT u.id, u.role_id, r.name AS 'rolename', u.employee_number, u.name, u.second_name, u.surname, u.email, u.phone_number, u.passport_series, u.passport_number, u.password FROM user AS u INNER JOIN role AS r ON u.role_id = r.id";
    private static final String INSERT_QUERY = "INSERT INTO user (id, role_id, employee_number, name, second_name, surname, email, phone_number, passport_series, passport_number, password) VALUES (DEFAULT, DEFAULT, DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE user SET role_id = ?, employee_number = ?, name = ?, second_name = ?, surname = ?, phone_number = ?, passport_series = ?, passport_number = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM user WHERE id = ?";


    public UserDao(Connection connection) {
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
    protected List<User> parseResultSet(ResultSet resultSet) throws PersistException {
        List<User> userList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user = new User();

                Long id = resultSet.getLong(User.ID_ALIAS);
                user.setId(id);

                Role role = new Role();
                Long roleId = resultSet.getLong(Role.FOREIGN_KEY_ID_ALIAS);
                role.setId(roleId);

                String roleName = resultSet.getString(Role.TABLE_NAME + Role.NAME_ALIAS);
                role.setName(roleName);
                user.setRole(role);

                String employeeNumber = resultSet.getString(User.EMPLOYEE_NUMBER_ALIAS);
                user.setEmployeeNumber(employeeNumber);

                String name = resultSet.getString(User.NAME_ALIAS);
                user.setName(name);

                String secondName = resultSet.getString(User.SECOND_NAME_ALIAS);
                user.setSecondName(secondName);

                String surname = resultSet.getString(User.SURNAME_ALIAS);
                user.setSurname(surname);

                String email = resultSet.getString(User.EMAIL_ALIAS);
                user.setEmail(email);

                String phoneNumber = resultSet.getString(User.PHONE_NUMBER_ALIAS);
                user.setPhoneNumber(phoneNumber);

                String passportSeries = resultSet.getString(User.PASSPORT_SERIES_ALIAS);
                user.setPassportSeries(passportSeries);

                String passportNumber = resultSet.getString(User.PASSPORT_NUMBER_ALIAS);
                user.setPassportNumber(passportNumber);

                String password = resultSet.getString(User.PASSWORD_ALIAS);
                user.setPassword(password);

                userList.add(user);
            }
        } catch (SQLException e) {
            throw new PersistException("Trouble in UserDao within parseResultSet", e);
        }
        return userList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User entity) throws PersistException {
        try {
            String name = entity.getName();
            statement.setString(1, name);

            String secondName = entity.getSecondName();
            statement.setString(2, secondName);

            String surname = entity.getSurname();
            statement.setString(3, surname);

            String email = entity.getEmail();
            statement.setString(4, email);

            String phoneNumber = entity.getPhoneNumber();
            statement.setString(5, phoneNumber);

            String passportSeries = entity.getPassportSeries();
            statement.setString(6, passportSeries);

            String passportNumber = entity.getPassportNumber();
            statement.setString(7, passportNumber);

            String password = entity.getPassword();
            statement.setString(8, password);

        } catch (SQLException e) {
            throw new PersistException("Trouble in UserDao within prepareStatementForInsert: " + e.getMessage(), e);

        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User entity) throws PersistException {
        try {
            Role userRole = entity.getRole();
            Long roleId = userRole.getId();
            statement.setLong(1, roleId);

            String employeeNumber = entity.getEmployeeNumber();
            statement.setString(2, employeeNumber);

            String name = entity.getName();
            statement.setString(3, name);

            String secondName = entity.getSecondName();
            statement.setString(4, secondName);

            String surname = entity.getSurname();
            statement.setString(5, surname);

            String phoneNumber = entity.getPhoneNumber();
            statement.setString(6, phoneNumber);

            String passportSeries = entity.getPassportSeries();
            statement.setString(7, passportSeries);

            String passportNumber = entity.getPassportNumber();
            statement.setString(8, passportNumber);

            Long id = entity.getId();
            statement.setLong(9, id);

        } catch (SQLException e) {
            throw new PersistException("Trouble in UserDao within prepareStatementForInsert", e);

        }
    }

    public User findOneByMailPass(User entity) throws PersistException {
        if (entity == null) {
            throw new PersistException("Error within UserDao findOneByMailPass(): Null entity object received");
        }

        List<User> result;
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ONE_BY_EMAIL_PASS)) {
            prepareStatementForFindOneByMailPass(statement, entity);
            ResultSet resultSet = statement.executeQuery();
            result = parseResultSet(resultSet);

        } catch (SQLException e) {
            throw new PersistException("Error within UserDao findOneByMailPass(): " + e.getMessage(), e);
        }

        if (result.isEmpty()) {
            return null;
        }

        if (result.size() > SINGLE_ROW) {
            throw new PersistException("Error within UserDao findOneByMailPass(): More than one record has been received");
        }


        return result.iterator().next();
    }



    private void prepareStatementForFindOneByMailPass(PreparedStatement statement, User entity) throws PersistException {
        try {
            String email = entity.getEmail();
            statement.setString(1, email);

            String password = entity.getPassword();
            statement.setString(2, password);

        } catch (SQLException e) {
            throw new PersistException("Error within UserDao prepareStatementForFindOneByMailPass(): " + e.getMessage(), e);
        }
    }

    public User findOneByMail(User entity) throws PersistException {
        if (entity == null) {
            throw new PersistException("Error within UserDao findOneByMail(): Null object received");
        }

        List<User> result;
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ONE_BY_EMAIL)) {
            prepareStatementForFindOneByMail(statement, entity);
            ResultSet resultSet = statement.executeQuery();
            result = parseResultSet(resultSet);

        } catch (SQLException e) {
            throw new PersistException("Error within UserDao findOneByMail(): " + e.getMessage(), e);
        }

        if (result.isEmpty()) {
            return null;
        }

        if (result.size() > SINGLE_ROW) {
            throw new PersistException("Error within UserDao findOneByMail(): More than one record has been received");
        }

        return result.iterator().next();
    }



    private void prepareStatementForFindOneByMail(PreparedStatement statement, User entity) throws PersistException {
        try {
            String email = entity.getEmail();
            statement.setString(1, email);

        } catch (SQLException e) {
            throw new PersistException("Error within UserDao prepareStatementForFindOneByMail(): " + e.getMessage(), e);
        }
    }
}
