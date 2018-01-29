package com.epam.training.library.daolayer.service;

import com.epam.training.library.daolayer.model.User;
import com.epam.training.library.daolayer.service.exception.BusinessException;
import com.epam.training.library.daolayer.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    void delete(User user) throws ServiceException;

    User findOneById(Long id) throws ServiceException;

    List<User> findAll() throws ServiceException;

    User findRegisteredUser(User user) throws ServiceException;

    void registration(User user) throws ServiceException, BusinessException;

    void update(User user) throws ServiceException;
}
