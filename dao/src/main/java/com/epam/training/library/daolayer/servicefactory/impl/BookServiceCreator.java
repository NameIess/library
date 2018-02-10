package com.epam.training.library.daolayer.servicefactory.impl;

import com.epam.training.library.daolayer.dao.BookDao;
import com.epam.training.library.daolayer.daofactory.DaoManager;
import com.epam.training.library.daolayer.model.Book;
import com.epam.training.library.daolayer.service.BookService;
import com.epam.training.library.daolayer.service.impl.BookServiceImpl;
import com.epam.training.library.daolayer.servicefactory.ServiceFactory;

public class BookServiceCreator implements ServiceFactory<BookService> {
    private BookDao bookDao;

    public BookServiceCreator() {
        DaoManager daoManager = DaoManager.getInstance();
        bookDao = daoManager.getDao(Book.class);
    }

    @Override
    public BookService create() {
        BookService bookService = new BookServiceImpl(bookDao);
        return bookService;
    }
}
