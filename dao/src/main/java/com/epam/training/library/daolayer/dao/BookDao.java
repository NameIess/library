package com.epam.training.library.daolayer.dao;

import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends AbstractDao<Book> {
    private static final String FIND_ALL_QUERY = "SELECT id, title, author, year_of_publishing, number_of_pages, description, amount FROM book";
    private static final String FIND_ONE_QUERY = "SELECT id, title, author, year_of_publishing, number_of_pages, description, amount FROM book WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO book (id, title, author, year_of_publishing, number_of_pages, description, amount) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE book SET title = ?, author = ?, year_of_publishing = ?, number_of_pages = ?, description = ?, amount = ? WHERE id = ?";
    private static final String UPDATE_AMOUNT_BY_ID_QUERY = "UPDATE book SET amount = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM book WHERE id = ?";
    private static final int SINGLE_ROW = 1;


    public BookDao(Connection connection) {
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
    protected List<Book> parseResultSet(ResultSet resultSet) throws PersistException {
        List<Book> bookList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Book book = new Book();

                Long id = resultSet.getLong(Book.ID_ALIAS);
                book.setId(id);

                String title = resultSet.getString(Book.TITLE_ALIAS);
                book.setTitle(title);

                String author = resultSet.getString(Book.AUTHOR_ALIAS);
                book.setAuthor(author);

                Short yearOfPublishing = resultSet.getShort(Book.YEAR_OF_PUBLISHING_ALIAS);
                book.setYearOfPublishing(yearOfPublishing);

                Short numberOfPages = resultSet.getShort(Book.NUMBER_OF_PAGES_ALIAS);
                book.setNumberOfPages(numberOfPages);

                String description = resultSet.getString(Book.DESCRIPTION_ALIAS);
                book.setDescription(description);

                Integer amount = resultSet.getInt(Book.AMOUNT_ALIAS);
                book.setAmount(amount);

                bookList.add(book);
            }
        } catch (SQLException e) {
            throw new PersistException("Error within BookDao parseResultSet(): " + e.getMessage(), e);
        } return bookList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Book entity) throws PersistException {
        try {
            String title = entity.getTitle();
            statement.setString(1, title);

            String author = entity.getAuthor();
            statement.setString(2, author);

            Short yearOfPublishing = entity.getYearOfPublishing();
            statement.setShort(3, yearOfPublishing);

            Short numberOfPages = entity.getNumberOfPages();
            statement.setShort(4, numberOfPages);

            String description = entity.getDescription();
            statement.setString(5, description);

            int amount = entity.getAmount();
            statement.setLong(6, amount);

        } catch (SQLException e) {
            throw new PersistException("Error within BookDao prepareStatementForInsert(): "  + e.getMessage(), e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Book entity) throws PersistException {
        try {
            String title = entity.getTitle();
            statement.setString(1, title);

            String author = entity.getAuthor();
            statement.setString(2, author);

            Long yearOfPublishing = Long.valueOf(entity.getYearOfPublishing());
            statement.setLong(3, yearOfPublishing);

            Long numberOfPages = Long.valueOf(entity.getNumberOfPages());
            statement.setLong(4, numberOfPages);

            String description = entity.getDescription();
            statement.setString(5, description);

            Integer amount = entity.getAmount();
            statement.setInt(6, amount);

            Long id = entity.getId();
            statement.setLong(7, id);

        } catch (SQLException e) {
            throw new PersistException("Error within BookDao prepareStatementForUpdate(): "  + e.getMessage(), e);
        }
    }

    public void updateAmountById(Book entity) throws PersistException {
        if (entity == null) {
            throw new PersistException("Error within BookDao updateAmountById(): Null entity object received");
        }
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_AMOUNT_BY_ID_QUERY)){
            prepareStatementForUpdateAmountById(statement, entity);
            int counter = statement.executeUpdate();
            if (counter != SINGLE_ROW) {
                throw new PersistException("Error within BookDao updateAmountById(): update query doesn't modify 1 record: " + counter);
            }
        } catch (SQLException e) {
            throw new PersistException("Error within BookDao updateAmountById(): " + e.getMessage(), e);
        }
    }

    private void prepareStatementForUpdateAmountById(PreparedStatement statement, Book entity) throws PersistException {
        try {
            Integer amount = entity.getAmount();
            statement.setInt(1, amount);

            Long id = entity.getId();
            statement.setLong(2, id);

        } catch (SQLException e) {
            throw new PersistException("Error within BookDao prepareStatementForUpdateAmountById(): " + e.getMessage(), e);
        }
    }
}
