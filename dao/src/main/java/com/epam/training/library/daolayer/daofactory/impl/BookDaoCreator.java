package com.epam.training.library.daolayer.daofactory.impl;

import com.epam.training.library.daolayer.connection.ConnectionManager;
import com.epam.training.library.daolayer.dao.BookDao;
import com.epam.training.library.daolayer.daofactory.DaoFactory;

public class BookDaoCreator implements DaoFactory<BookDao> {

    @Override
    public BookDao create(ConnectionManager connectionManager) {
        BookDao bookDao = new BookDao(connectionManager);
        return bookDao;
    }
}
