package com.epam.training.library.daolayer.service;

import com.epam.training.library.daolayer.model.Role;
import com.epam.training.library.daolayer.service.exception.ServiceException;

import java.util.List;

/**
 * Contains the basic operations interaction with the roles.
 */
public interface RoleService extends Service {

    /**
     * Returns a list of the all user roles
     *
     * @return a list of the all user roles
     *
     * @throws ServiceException when data access occurs
     */
    List<Role> findAll() throws ServiceException;

    Role findOneById(Long id) throws ServiceException;
}
