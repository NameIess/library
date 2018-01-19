package service;

import dao.exception.PersistException;
import model.User;
import service.exception.ServiceException;

import java.util.List;

public interface UserService {
    void delete(User user) throws ServiceException;

    User findOneById(Long id) throws ServiceException;

    List<User> findAll() throws ServiceException;

    User findRegisteredUser(User user) throws ServiceException;

    void registration(User user) throws ServiceException;

    void update(User user) throws ServiceException;
}
