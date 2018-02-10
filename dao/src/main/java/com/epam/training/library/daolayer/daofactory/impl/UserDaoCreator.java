package com.epam.training.library.daolayer.daofactory.impl;

import com.epam.training.library.daolayer.connection.ConnectionManager;
import com.epam.training.library.daolayer.dao.UserDao;
import com.epam.training.library.daolayer.daofactory.DaoFactory;

public class UserDaoCreator implements DaoFactory<UserDao> {

    @Override
    public UserDao create(ConnectionManager connectionManager) {
        UserDao userDao = new UserDao(connectionManager);
        return userDao;
    }
}
