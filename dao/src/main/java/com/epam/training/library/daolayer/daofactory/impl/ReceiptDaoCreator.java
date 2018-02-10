package com.epam.training.library.daolayer.daofactory.impl;

import com.epam.training.library.daolayer.connection.ConnectionManager;
import com.epam.training.library.daolayer.dao.ReceiptDao;
import com.epam.training.library.daolayer.daofactory.DaoFactory;

public class ReceiptDaoCreator implements DaoFactory<ReceiptDao> {

    @Override
    public ReceiptDao create(ConnectionManager connectionManager) {
        ReceiptDao receiptDao = new ReceiptDao(connectionManager);
        return receiptDao;
    }
}
