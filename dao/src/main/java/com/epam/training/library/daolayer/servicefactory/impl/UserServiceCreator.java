package com.epam.training.library.daolayer.servicefactory.impl;

import com.epam.training.library.daolayer.dao.UserDao;
import com.epam.training.library.daolayer.daofactory.DaoManager;
import com.epam.training.library.daolayer.model.User;
import com.epam.training.library.daolayer.service.UserService;
import com.epam.training.library.daolayer.service.impl.UserServiceImpl;
import com.epam.training.library.daolayer.servicefactory.ServiceFactory;
import com.epam.training.library.daolayer.util.Encryptable;
import com.epam.training.library.daolayer.util.PasswordEncoder;
import com.epam.training.library.daolayer.validator.UserSignInValidator;
import com.epam.training.library.daolayer.validator.Verifiable;

public class UserServiceCreator implements ServiceFactory<UserService> {
    private Encryptable passwordEncoder;
    private Verifiable<User> userValidator;
    private UserDao userDao;

    public UserServiceCreator() {
        DaoManager daoManager = DaoManager.getInstance();
        passwordEncoder = new PasswordEncoder();
        userValidator = new UserSignInValidator();
        userDao = daoManager.getDao(User.class);
    }

    @Override
    public UserService create() {
        UserService userService = new UserServiceImpl(userDao, passwordEncoder, userValidator);
        return userService;
    }
}
