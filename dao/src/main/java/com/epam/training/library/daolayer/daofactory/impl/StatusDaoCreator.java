package com.epam.training.library.daolayer.daofactory.impl;

import com.epam.training.library.daolayer.connection.ConnectionManager;
import com.epam.training.library.daolayer.dao.StatusDao;
import com.epam.training.library.daolayer.daofactory.DaoFactory;

public class StatusDaoCreator implements DaoFactory<StatusDao> {

    @Override
    public StatusDao create(ConnectionManager connectionManager) {
        StatusDao statusDao = new StatusDao(connectionManager);
        return statusDao;
    }
}
