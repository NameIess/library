package com.epam.training.library.daolayer.servicefactory.impl;

import com.epam.training.library.daolayer.dao.BookDao;
import com.epam.training.library.daolayer.dao.ReceiptDao;
import com.epam.training.library.daolayer.daofactory.DaoManager;
import com.epam.training.library.daolayer.model.Book;
import com.epam.training.library.daolayer.model.Receipt;
import com.epam.training.library.daolayer.service.ReceiptService;
import com.epam.training.library.daolayer.service.impl.ReceiptServiceImpl;
import com.epam.training.library.daolayer.servicefactory.ServiceFactory;

public class ReceiptServiceCreator implements ServiceFactory<ReceiptService> {
    private ReceiptDao receiptDao;
    private BookDao bookDao;

    public ReceiptServiceCreator() {
        DaoManager daoManager = DaoManager.getInstance();
        receiptDao = daoManager.getDao(Receipt.class);
        bookDao = daoManager.getDao(Book.class);
    }

    @Override
    public ReceiptService create() {
        ReceiptService receiptService = new ReceiptServiceImpl(receiptDao, bookDao);
        return receiptService;
    }
}
