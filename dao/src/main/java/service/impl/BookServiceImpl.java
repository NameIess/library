package service.impl;

import dao.BookDao;
import dao.exception.PersistException;
import model.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.BookService;
import service.exception.ServiceException;

import java.util.List;

public class BookServiceImpl implements BookService {
    private static final Logger Log = LogManager.getLogger(BookServiceImpl.class.getSimpleName());
    private BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save(Book book) throws ServiceException {
        try {
            bookDao.startTransaction();
            bookDao.create(book);
            bookDao.commit();
        } catch (PersistException e) {
            bookDao.rollback();
            throw new ServiceException("Error within BookServiceImpl save(): " + e.getMessage(), e);
        } finally {
            bookDao.releaseConnection();
        }
    }

    @Override
    public void delete(Book book) throws ServiceException {
        try {
            bookDao.delete(book);
        } catch (PersistException e) {
            throw new ServiceException("Error within BookServiceImpl delete(): " + e.getMessage(), e);
        } finally {
            bookDao.releaseConnection();
        }
    }

    @Override
    public Book findOneById(Long id) throws ServiceException {
        Book book;
        try {
            book = bookDao.findOne(id);
        } catch (PersistException e) {
            throw new ServiceException("Error within BookServiceImpl findOneById(): " + e.getMessage(), e);
        } finally {
            bookDao.releaseConnection();
        }
        return book;
    }

    @Override
    public List<Book> findAll() throws ServiceException {
        List<Book> bookList;
        try {
            bookList = bookDao.findAll();
        } catch (PersistException e) {
            throw new ServiceException("Error within BookServiceImpl findAll(): " + e.getMessage(), e);
        } finally {
            bookDao.releaseConnection();
        }
        return bookList;
    }
}
