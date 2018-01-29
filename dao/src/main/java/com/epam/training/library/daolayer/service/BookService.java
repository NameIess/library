package com.epam.training.library.daolayer.service;

import com.epam.training.library.daolayer.model.Book;
import com.epam.training.library.daolayer.service.exception.ServiceException;

import java.util.List;

public interface BookService {
    void save(Book book) throws ServiceException;

    void delete(Book book) throws ServiceException;

    Book findOneById(Long id) throws ServiceException;

    List<Book> findAll() throws ServiceException;

}
