package service.impl;

import dao.RoleDao;
import dao.exception.PersistException;
import model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.RoleService;
import service.exception.ServiceException;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private static final Logger Log = LogManager.getLogger(RoleServiceImpl.class.getSimpleName());
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> findAll() throws ServiceException {
        List<Role> roleList;
        try {
            roleList = roleDao.findAll();
        } catch (PersistException e) {
            throw new ServiceException("Error within RoleService findAll(): " + e.getMessage(), e);
        } finally {
            roleDao.releaseConnection();
        }
        Log.info("Roles " + roleList + " have been received from DB");
        return roleList;
    }

    @Override
    public Role findOneById(Long id) throws ServiceException {
        Role role;
        try {
            role = roleDao.findOne(id);
        } catch (PersistException e) {
            throw new ServiceException("Error within RoleService findOneById(): " + e.getMessage(), e);
        } finally {
            roleDao.releaseConnection();
        }
        Log.info("Role " + role + " has been received from DB by ID - " + id);
        return role;
    }

    @Override
    public void update(Role role) throws ServiceException {
        try {
            roleDao.startTransaction();
            roleDao.update(role);
            roleDao.commit();

            Log.info("Role has been updated");
        } catch (PersistException e) {
            roleDao.rollback();
            throw new ServiceException("Error within RoleService update(): " + e.getMessage(), e);
        } finally {
            roleDao.releaseConnection();
        }

    }

    @Override
    public void delete(Role role) throws ServiceException {
        try {
            roleDao.delete(role);
            Log.info("Role " + role + " has been removed from DB");
        } catch (PersistException e) {
            throw new ServiceException("Error within RoleService delete(): " + e.getMessage(), e);
        } finally {
            roleDao.releaseConnection();
        }
    }
}
