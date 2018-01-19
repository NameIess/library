package service.impl;

import dao.UserDao;
import dao.exception.PersistException;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;
import service.exception.ServiceException;
import util.Encryptable;
import validator.Verifiable;

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
    public void delete(User user) throws ServiceException {
        try {
            userDao.delete(user);
        } catch (PersistException e) {
            throw new ServiceException("Error within UserService delete(): " + e.getMessage(), e);
        } finally {
            userDao.releaseConnection();
        }
    }

    @Override
    public User findOneById(Long id) throws ServiceException {
        User user;
        try {
            user = userDao.findOne(id);
        } catch (PersistException e) {
            throw new ServiceException("Error within UserService findOneById(): " + e.getMessage(), e);
        } finally {
            userDao.releaseConnection();
        }
        return user;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> userList;
        try {
            userList = userDao.findAll();
        } catch (PersistException e) {
            throw new ServiceException("Error within UserService findAll(): " + e.getMessage(), e);
        } finally {
            userDao.releaseConnection();
        }
        return userList;
    }

    @Override
    public User findRegisteredUser(User user) throws ServiceException {
        if (!isValidInputData(user)) {
            return null;
        }
        String encryptedPassword = encryptPassword(user);
        user.setPassword(encryptedPassword);
        User registeredUser;
        try {
            registeredUser = userDao.findOneByMailPass(user);
        } catch (PersistException e) {
            throw new ServiceException("Error within UserService findRegisteredUser(): " + e.getMessage(), e);
        } finally {
            userDao.releaseConnection();
        }
        return registeredUser;
    }


    @Override
    public void registration(User user) throws ServiceException {
        String encryptedPassword = encryptPassword(user);
        user.setPassword(encryptedPassword);
        try {
            userDao.startTransaction();
            Log.info("User before registration: " + user);
            User registeredUser = userDao.findOneByMail(user);
            if (registeredUser == null) {
                userDao.create(user);
                userDao.commit();
            } else {
                userDao.rollback();
                throw new ServiceException("This user is registered already.");
            }

        } catch (PersistException e) {
            userDao.rollback();
            throw new ServiceException("Error within UserService save(): " + e.getMessage(), e);
        } finally {
            userDao.releaseConnection();
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        try {
            userDao.startTransaction();
            userDao.update(user);
            userDao.commit();
        } catch (PersistException e) {
            userDao.rollback();
            throw new ServiceException("Error within UserService update(): " + e.getMessage(), e);
        } finally {
            userDao.releaseConnection();
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
