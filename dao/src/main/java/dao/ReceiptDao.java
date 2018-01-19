package dao;

import dao.exception.PersistException;
import model.Book;
import model.Receipt;
import model.Status;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDao extends AbstractDao<Receipt> {
    private static final Logger Log = LogManager.getLogger(ReceiptDao.class.getSimpleName());

    private static final int SINGLE_ROW = 1;
    private static final String FIND_ALL_QUERY = "SELECT r.id, r.status_id, r.user_id, r.book_id, r.quantity, r.is_subscribtion, r.term," +
        " b.author, b.title, b.number_of_pages, b.description, b.year_of_publishing, b.amount, s.name, u.name AS 'username', u.surname AS 'usersurname' " +
        "FROM receipt AS r " +
        "INNER JOIN book AS b ON r.book_id = b.id " +
        "INNER JOIN status AS s ON r.status_id = s.id " +
        "INNER JOIN user AS u ON r.user_id = u.id ";

    private static final String INSERT_QUERY = "INSERT INTO receipt (id, status_id, user_id, book_id, quantity, is_subscribtion, term) VALUES (DEFAULT, DEFAULT, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE receipt SET status_id = ?, user_id = ?, book_id = ?, quantity = ?, is_subscribtion = ?, term = ? WHERE id = ?";
    private static final String UPDATE_STATUS_QUERY = "UPDATE receipt SET status_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM receipt WHERE id = ?";
    private static final String FIND_ONE_QUERY = "SELECT r.id, r.status_id, r.user_id, r.book_id, r.quantity, r.is_subscribtion, r.term," +
            " b.author, b.title, b.number_of_pages, b.description, b.year_of_publishing, b.amount, s.name, u.name AS 'username', u.surname AS 'usersurname' " +
            "FROM receipt AS r " +
            "INNER JOIN book AS b ON r.book_id = b.id " +
            "INNER JOIN status AS s ON r.status_id = s.id " +
            "INNER JOIN user AS u ON r.user_id = u.id  WHERE r.id = ?";
    private static final String FIND_ALL_BY_USER_ID_QUERY = "SELECT r.id, r.status_id, r.user_id, r.book_id, r.quantity, r.is_subscribtion, r.term," +
            " b.author, b.title, b.number_of_pages, b.description, b.year_of_publishing, b.amount," +
            " s.name, " +
            " u.name AS 'username', u.surname AS 'usersurname' " +
            "FROM receipt AS r " +
            "INNER JOIN book AS b ON r.book_id = b.id " +
            "INNER JOIN status AS s ON r.status_id = s.id " +
            "INNER JOIN user AS u ON r.user_id = u.id " +
            "WHERE u.id = ?";

    public ReceiptDao(Connection connection) {
        super(connection);
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
    protected String getFindOneQuery() {
        return FIND_ONE_QUERY;
    }

    @Override
    protected List<Receipt> parseResultSet(ResultSet resultSet) throws PersistException {
        List<Receipt> receiptList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Receipt receipt = new Receipt();

                Long receiptId = resultSet.getLong(Receipt.ID_ALIAS);
                receipt.setId(receiptId);

                Status status = new Status();
                Long statusId = resultSet.getLong(Status.FOREIGN_KEY_ALIAS);
                status.setId(statusId);

                String statusName = resultSet.getString(Status.NAME_ALIAS);
                status.setName(statusName);
                receipt.setStatus(status);

                User user = new User();
                Long userId = resultSet.getLong(User.FOREIGN_KEY_ID_ALIAS);
                user.setId(userId);
                String userName = resultSet.getString(User.TABLE_NAME + User.NAME_ALIAS);
                user.setName(userName);
                String surname = resultSet.getString(User.TABLE_NAME + User.SURNAME_ALIAS);
                user.setSurname(surname);
                receipt.setUser(user);

                Book book = new Book();
                Long bookId = resultSet.getLong(Book.FOREIGN_KEY_ALIAS);
                book.setId(bookId);
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
                receipt.setBook(book);

                Integer quantity = resultSet.getInt(Receipt.QUANTITY_ALIAS);
                receipt.setQuantity(quantity);

                Boolean aBoolean = resultSet.getBoolean(Receipt.IS_SUBSCRIPTION_ALIAS);
                receipt.setSubscription(aBoolean);

                String term = resultSet.getString(Receipt.TERM_ALIAS);
                receipt.setTerm(term);

                receiptList.add(receipt);
            }
        } catch (SQLException e) {
            throw new PersistException("Error within ReceiptDao parseResultSet():" + e.getMessage(), e);
        }
        return receiptList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Receipt entity) throws PersistException {
        try {
            User user = entity.getUser();
            Long userId = user.getId();
            statement.setLong(1, userId);

            Book book = entity.getBook();
            Long bookId = book.getId();
            statement.setLong(2, bookId);

            Integer quantity = entity.getQuantity();
            statement.setInt(3, quantity);

            Boolean isSubscription = entity.isSubscription();
            statement.setBoolean(4, isSubscription);

            String term = entity.getTerm();
            statement.setString(5, term);
        } catch (SQLException e) {
            throw new PersistException("Error within ReceiptDao prepareStatementForInsert():" + e.getMessage(), e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Receipt entity) throws PersistException {
        try {
            Status status = entity.getStatus();
            Long statusId = status.getId();
            statement.setLong(1, statusId);

            User user = entity.getUser();
            Long userId = user.getId();
            statement.setLong(2, userId);

            Book book = entity.getBook();
            Long bookId = book.getId();
            statement.setLong(3, bookId);

            int quantity = entity.getQuantity();
            statement.setInt(4, quantity);

            Boolean isSubscription = entity.isSubscription();
            statement.setBoolean(5, isSubscription);

            String term = entity.getTerm();
            statement.setString(6, term);

        } catch (SQLException e) {
            throw new PersistException("Error within ReceiptDao prepareStatementForUpdate():" + e.getMessage(), e);
        }
    }


    private void prepareStatementForUpdateStatusId(PreparedStatement statement, Receipt entity) throws PersistException {
        try {
            Status status = entity.getStatus();
            Long statusId = status.getId();
            statement.setLong(1, statusId);

            Long id = entity.getId();
            statement.setLong(2, id);
        } catch (SQLException e) {
            throw new PersistException("Error within ReceiptDao prepareStatementForUpdateStatusId(): " + e.getMessage(), e);
        }
    }

    public void updateStatusById(Receipt entity) throws PersistException {
        if (entity == null) {
            throw new PersistException("Error within ReceiptDao updateStatusById(): Null entity object received");
        }

        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_QUERY)) {
            prepareStatementForUpdateStatusId(statement, entity);
            int counter = statement.executeUpdate();
            if (counter != SINGLE_ROW) {
                throw new PersistException("Error within ReceiptDao updateStatusById(): Update query doesn't modify 1 record: " + counter + " records affected");
            }
        } catch (SQLException e) {
            throw new PersistException("Error within ReceiptDao updateStatusById(): " + e.getMessage(), e);
        }
    }
    
    private void prepareStatementForFindAllByUserId(PreparedStatement statement, Long entity) throws PersistException {
        try {
            statement.setLong(1, entity);
            
        } catch (SQLException e) {
            throw new PersistException("Error within ReceiptDao prepareStatementForFindAllByUserId(): " + e.getMessage(), e);
        }
    }
    
    public List<Receipt> findAllByUserId(Long entity) throws PersistException {
        if (entity == null) {
            throw new PersistException("Error within ReceiptDao findAllByUserId(): Null entity object received");
        }
        
        List<Receipt> receipts;
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_USER_ID_QUERY)){
            prepareStatementForFindAllByUserId(statement, entity);
            ResultSet resultSet = statement.executeQuery();
            receipts = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new PersistException("Error within ReceiptDao findAllByUserId(): " + e.getMessage(), e);
        }
        
        return receipts;
    }
}
