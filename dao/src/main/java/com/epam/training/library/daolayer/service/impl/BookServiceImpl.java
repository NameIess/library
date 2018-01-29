package com.epam.training.library.daolayer.service.impl;

import com.epam.training.library.daolayer.dao.BookDao;
import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.training.library.daolayer.service.BookService;
import com.epam.training.library.daolayer.service.exception.ServiceException;

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

            Log.info("Book " + book + " has been saved to DB");
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
            Log.info("Book " + book + " has been removed from DB");
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
        Log.info("Book " + book + " has been received from DB by ID - " + id);
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
        Log.info("Books " + bookList + " have been received from DB");
        return bookList;
    }
}
