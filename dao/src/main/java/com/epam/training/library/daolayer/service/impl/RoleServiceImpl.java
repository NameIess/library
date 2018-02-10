package com.epam.training.library.daolayer.service.impl;

import com.epam.training.library.daolayer.dao.RoleDao;
import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.training.library.daolayer.service.RoleService;
import com.epam.training.library.daolayer.service.exception.ServiceException;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private static final Logger Log = LogManager.getLogger(RoleServiceImpl.class.getSimpleName());
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> findAll() throws ServiceException {
        try {
            List<Role> roleList = roleDao.findAll();
            Log.info("Roles " + roleList + " have been received from DB");
            return roleList;
        } catch (PersistException e) {
            throw new ServiceException("Error within RoleService findAll(): " + e.getMessage(), e);
        } finally {
            roleDao.getConnectionManager().releaseConnection();
        }
    }

    @Override
    public Role findOneById(Long id) throws ServiceException {
        try {
            Role role = roleDao.findOne(id);
            return role;
        } catch (PersistException e) {
            throw new ServiceException("Error within RoleService findOneById(): " + e.getMessage(), e);
        } finally {
            roleDao.getConnectionManager().releaseConnection();
        }
    }
}
