package com.epam.training.library.daolayer.servicefactory.impl;

import com.epam.training.library.daolayer.dao.RoleDao;
import com.epam.training.library.daolayer.daofactory.DaoManager;
import com.epam.training.library.daolayer.model.Role;
import com.epam.training.library.daolayer.service.RoleService;
import com.epam.training.library.daolayer.service.impl.RoleServiceImpl;
import com.epam.training.library.daolayer.servicefactory.ServiceFactory;

public class RoleServiceCreator implements ServiceFactory<RoleService> {
    private RoleDao roleDao;

    public RoleServiceCreator() {
        DaoManager daoManager = DaoManager.getInstance();
        roleDao = daoManager.getDao(Role.class);
    }

    @Override
    public RoleService create() {
        RoleService roleService = new RoleServiceImpl(roleDao);
        return roleService;
    }
}
