package com.epam.training.library.daolayer.service.impl;

import com.epam.training.library.daolayer.dao.UserDao;
import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.training.library.daolayer.service.UserService;
import com.epam.training.library.daolayer.service.exception.BusinessException;
import com.epam.training.library.daolayer.service.exception.ServiceException;
import com.epam.training.library.daolayer.util.Encryptable;
import com.epam.training.library.daolayer.validator.Verifiable;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger Log = LogManager.getLogger(UserServiceImpl.class.getSimpleName());
    private UserDao userDao;
    private Encryptable passwordEncoder;
    private Verifiable<User> userSignInValidator;


    public UserServiceImpl(UserDao userDao, Encryptable passwordEncoder, Verifiable<User> userSignInValidator) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.userSignInValidator = userSignInValidator;
    }


    @Override
    public void delete(Long userId) throws ServiceException {
        try {
            userDao.delete(userId);
            Log.info("User with the id: " + userId + " has been removed from DB");
        } catch (PersistException e) {
            throw new ServiceException("Error within UserService delete(): " + e.getMessage(), e);
        } finally {
            userDao.getConnectionManager().releaseConnection();
        }
    }

    @Override
    public User findOneById(Long id) throws ServiceException {
        try {
            User user = userDao.findOne(id);
            Log.info("User " + user + " has been received from DB by ID - " + id);
            return user;
        } catch (PersistException e) {
            throw new ServiceException("Error within UserService findOneById(): " + e.getMessage(), e);
        } finally {
            userDao.getConnectionManager().releaseConnection();
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            List<User> userList = userDao.findAll();
            Log.info("Users " + userList + " have been received from DB");
            return userList;
        } catch (PersistException e) {
            throw new ServiceException("Error within UserService findAll(): " + e.getMessage(), e);
        } finally {
            userDao.getConnectionManager().releaseConnection();
        }
    }

    @Override
    public User findRegisteredUser(User user) throws ServiceException {
        if (!isValidInputData(user)) {
            return null;
        }
        try {
            String encryptedPassword = encryptPassword(user);
            user.setPassword(encryptedPassword);
            User registeredUser = userDao.findOneByMailPass(user);
            Log.info("Registered user " + registeredUser + " have been received from DB by personal data");
            return registeredUser;
        } catch (PersistException e) {
            throw new ServiceException("Error within UserService findRegisteredUser(): " + e.getMessage(), e);
        } finally {
            userDao.getConnectionManager().releaseConnection();
        }
    }


    @Override
    public void registration(User user) throws ServiceException, BusinessException {
        try {
            String encryptedPassword = encryptPassword(user);
            user.setPassword(encryptedPassword);
            userDao.getConnectionManager().startTransaction();
            User registeredUser = userDao.findOneByMail(user);
            if (registeredUser == null) {
                userDao.create(user);
                userDao.getConnectionManager().commitTransaction();

                Log.info("User " + user + " has been saved to DB");
            } else {
                userDao.getConnectionManager().rollbackTransaction();
                throw new BusinessException("The user with this email is registered already");
            }
        } catch (PersistException e) {
            userDao.getConnectionManager().rollbackTransaction();
            throw new ServiceException("Error within UserService save(): " + e.getMessage(), e);
        } finally {
            userDao.getConnectionManager().releaseConnection();
        }
    }

    @Override
    public void update(User updatableUser) throws ServiceException {
        try {
            Long updatedUserId = updatableUser.getId();
            User userBeforeUpdate = userDao.findOne(updatedUserId);
            if (!userBeforeUpdate.equals(updatableUser)) {
                userDao.getConnectionManager().startTransaction();
                userDao.update(updatableUser);
                userDao.getConnectionManager().commitTransaction();
                Log.info("User has been updated to state: " + updatableUser);
            }
        } catch (PersistException e) {
            userDao.getConnectionManager().rollbackTransaction();
            throw new ServiceException("Error within UserService update(): " + e.getMessage(), e);
        } finally {
            userDao.getConnectionManager().releaseConnection();
        }
    }

    private String encryptPassword(User user) {
        String password = user.getPassword();
        String email = user.getEmail();
        String encryptedPassword = passwordEncoder.encryptMd5WithPostfixSalt(password, email);
        return encryptedPassword;
    }

    private boolean isValidInputData(User user) {
        boolean isValidInputData = userSignInValidator.validate(user);
        return isValidInputData;
    }
}
