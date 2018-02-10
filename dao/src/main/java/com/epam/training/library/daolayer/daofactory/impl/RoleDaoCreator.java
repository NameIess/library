package com.epam.training.library.daolayer.daofactory.impl;

import com.epam.training.library.daolayer.connection.ConnectionManager;
import com.epam.training.library.daolayer.dao.RoleDao;
import com.epam.training.library.daolayer.daofactory.DaoFactory;

public class RoleDaoCreator implements DaoFactory<RoleDao> {

    @Override
    public RoleDao create(ConnectionManager connectionManager) {
        RoleDao roleDao = new RoleDao(connectionManager);
        return roleDao;
    }
}
