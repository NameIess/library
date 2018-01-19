package service;

import model.Book;
import service.exception.ServiceException;

import java.util.List;

public interface BookService {
    void save(Book book) throws ServiceException;

    void delete(Book book) throws ServiceException;

    Book findOneById(Long id) throws ServiceException;

    List<Book> findAll() throws ServiceException;

}
