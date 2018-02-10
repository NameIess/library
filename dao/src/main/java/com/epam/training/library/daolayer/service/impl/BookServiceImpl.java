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
    public Book findOneById(Long id) throws ServiceException {
        try {
            Book book = bookDao.findOne(id);
            Log.info("Book " + book + " has been received from DB by ID - " + id);
            return book;
        } catch (PersistException e) {
            throw new ServiceException("Error within BookServiceImpl findOneById(): " + e.getMessage(), e);
        } finally {
            bookDao.getConnectionManager().releaseConnection();
        }
    }

    @Override
    public List<Book> findAll() throws ServiceException {
        try {
            List<Book> bookList = bookDao.findAll();
            Log.info("Books " + bookList + " have been received from DB");
            return bookList;
        } catch (PersistException e) {
            throw new ServiceException("Error within BookServiceImpl findAll(): " + e.getMessage(), e);
        } finally {
            bookDao.getConnectionManager().releaseConnection();
        }
    }

    @Override
    public void update(Book updatableBook) throws ServiceException {
        try {
            Long updatedBookId = updatableBook.getId();
            Book bookBeforeUpdate = bookDao.findOne(updatedBookId);
            if (!bookBeforeUpdate.equals(updatableBook)) {
                bookDao.getConnectionManager().startTransaction();
                bookDao.update(updatableBook);
                bookDao.getConnectionManager().commitTransaction();
                Log.info("Book has been updated to state: " + updatableBook);
            }
        } catch (PersistException e) {
            bookDao.getConnectionManager().rollbackTransaction();
            throw new ServiceException("Error within BookService update(): " + e.getMessage(), e);
        } finally {
            bookDao.getConnectionManager().releaseConnection();
        }
    }
}
