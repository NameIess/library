package service.impl;

import dao.UserDao;
import dao.exception.PersistException;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resources.ResourceData;
import service.UserService;
import service.exception.ServiceException;
import util.Encryptable;
import util.PasswordEncoder;
import validator.UserSignInValidator;
import validator.Verifiable;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserService userService;
    private UserDao userDao;
    private Verifiable<User> validator;
    private Encryptable passwordEncoder;


    @Before
    public void doSetup() {
        userDao = mock(UserDao.class);
        passwordEncoder = mock(PasswordEncoder.class);
        validator = new UserSignInValidator();
        userService = new UserServiceImpl(userDao, passwordEncoder, validator);
    }

    @Test
    public void shouldSaveUserToDatabaseWhenUserValid() throws PersistException, ServiceException {
        when(passwordEncoder.encryptMd5WithPostfixSalt(anyString(), anyString())).thenReturn("string");

        userService.registration(ResourceData.user);

        verify(userDao, times(ResourceData.NUMBER_1)).create(any(User.class));
        verify(passwordEncoder, times(ResourceData.NUMBER_1)).encryptMd5WithPostfixSalt(anyString(), anyString());
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionWhenUserAlreadyExists() throws PersistException, ServiceException {
        when(passwordEncoder.encryptMd5WithPostfixSalt(anyString(), anyString())).thenReturn("string");
        when(userDao.findOneByMail(ResourceData.user)).thenThrow(ServiceException.class);
        userService.registration(ResourceData.user);
    }

    @Test
    public void shouldDeleteUserWhenUserValid() throws PersistException, ServiceException {
        userService.delete(ResourceData.user);
        verify(userDao, times(ResourceData.NUMBER_1)).delete(any(User.class));
    }

    @Test
    public void shouldReturnOneUserWhenUserIdValid() throws PersistException, ServiceException {
        when(userDao.findOne(anyLong())).thenReturn(ResourceData.user);
        User actualUser = userService.findOneById(ResourceData.ENTITY_ID_1);
        verify(userDao, times(ResourceData.NUMBER_1)).findOne(ResourceData.ENTITY_ID_1);
        Long actualUserId = actualUser.getId();
        Assert.assertEquals(actualUserId, ResourceData.user.getId());
        Assert.assertEquals(actualUser, ResourceData.user);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionWhenUserValid() throws PersistException, ServiceException {
        when(userDao.findOne(anyLong())).thenThrow(ServiceException.class);
        userService.findOneById(ResourceData.ENTITY_ID_1);
    }

    @Test
    public void shouldReturnEntityList() throws PersistException, ServiceException {
        List<User> expectedList = new ArrayList<>();
        doReturn(expectedList).when(userDao).findAll();
        List<User> currentList = userService.findAll();

        Assert.assertEquals(expectedList, currentList);
    }
}
