package service;

import model.Role;
import service.exception.ServiceException;

import java.util.List;

public interface RoleService {
    List<Role> findAll() throws ServiceException;

    Role findOneById(Long id) throws ServiceException;

    void update(Role role) throws ServiceException;

    void delete(Role role) throws ServiceException;
}
