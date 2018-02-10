package com.epam.training.library.daolayer.service;

import com.epam.training.library.daolayer.model.Book;
import com.epam.training.library.daolayer.service.exception.ServiceException;

import java.util.List;

/**
 * Contains the basic operations interaction with the books.
 */
public interface BookService extends Service {

    /**
     * Returns a particular book with the specified id
     *
     * @param id — a book identifier that is used to find a required instance
     * @return an instance of the book with the given id
     *
     * @throws ServiceException when data access occurs
     */
    Book findOneById(Long id) throws ServiceException;

    /**
     * Returns a list of all books
     *
     * @return a list of all books
     *
     * @throws ServiceException when data access occurs
     */
    List<Book> findAll() throws ServiceException;

    /**
     * Updates an existing book using the specified instance, if they are differ
     *
     * @param updatableBook — an instance of the updated book
     * @throws ServiceException when data access occurs
     */
    void update(Book updatableBook) throws ServiceException;
}
