package com.epam.training.library.daolayer.service.impl;

import com.epam.training.library.daolayer.dao.UserDao;
import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import resources.ResourceData;
import com.epam.training.library.daolayer.service.UserService;
import com.epam.training.library.daolayer.service.exception.BusinessException;
import com.epam.training.library.daolayer.service.exception.ServiceException;
import com.epam.training.library.daolayer.util.Encryptable;
import com.epam.training.library.daolayer.util.PasswordEncoder;
import com.epam.training.library.daolayer.validator.UserSignInValidator;
import com.epam.training.library.daolayer.validator.Verifiable;

import java.util.ArrayList;
import java.util.List;


@RunWith(PowerMockRunner.class)
@PrepareForTest(UserServiceImpl.class)
@PowerMockIgnore("javax.management.*")
public class UserServiceImplTest {

    private UserService underTest;
    private UserDao userDao;
    private Verifiable<User> validator;
    private Encryptable passwordEncoder;


    @Before
    public void doSetup() {
        userDao = Mockito.mock(UserDao.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        validator = new UserSignInValidator();
        underTest = new UserServiceImpl(userDao, passwordEncoder, validator);
    }

    @Test
    public void shouldReturnNullWhenInputDataInvalid() throws Exception {
        UserService spyUnderTest = PowerMockito.spy(underTest);
        PowerMockito.doReturn(false).when(spyUnderTest, ResourceData.IS_VALID_INPUT_DATA_METHOD_NAME, ResourceData.userInstance);
        PowerMockito.doReturn(ResourceData.SAMPLE_STRING).when(spyUnderTest, ResourceData.ENCRYPT_PASSWORD_METHOD_NAME, ResourceData.userInstance);
        Mockito.when(userDao.findOneByMailPass(ResourceData.userInstance)).thenReturn(ResourceData.userInstance);

        User actualUser = spyUnderTest.findRegisteredUser(ResourceData.userInstance);
        PowerMockito.verifyPrivate(spyUnderTest, Mockito.times(ResourceData.ONE_TIME)).invoke(ResourceData.IS_VALID_INPUT_DATA_METHOD_NAME, ResourceData.userInstance);
        PowerMockito.verifyPrivate(spyUnderTest, Mockito.times(ResourceData.ZERO_INTEGER_VALUE)).invoke(ResourceData.ENCRYPT_PASSWORD_METHOD_NAME, ResourceData.userInstance);
        Mockito.verify(userDao, Mockito.times(ResourceData.ZERO_INTEGER_VALUE)).findOneByMailPass(ResourceData.userInstance);

        Assert.assertNull(actualUser);
    }

    @Test
    public void shouldReturnUserWhenInputDataValidAndUserExists() throws Exception {
        UserService spyUnderTest = PowerMockito.spy(underTest);
        PowerMockito.doReturn(true).when(spyUnderTest, ResourceData.IS_VALID_INPUT_DATA_METHOD_NAME, ResourceData.userInstance);
        PowerMockito.doReturn(ResourceData.SAMPLE_STRING).when(spyUnderTest, ResourceData.ENCRYPT_PASSWORD_METHOD_NAME, ResourceData.userInstance);
        Mockito.when(userDao.findOneByMailPass(ResourceData.userInstance)).thenReturn(ResourceData.userInstance);

        User actualUser = spyUnderTest.findRegisteredUser(ResourceData.userInstance);
        PowerMockito.verifyPrivate(spyUnderTest, Mockito.times(ResourceData.ONE_TIME)).invoke(ResourceData.IS_VALID_INPUT_DATA_METHOD_NAME, ResourceData.userInstance);
        PowerMockito.verifyPrivate(spyUnderTest, Mockito.times(ResourceData.ONE_TIME)).invoke(ResourceData.ENCRYPT_PASSWORD_METHOD_NAME, ResourceData.userInstance);
        Mockito.verify(userDao, Mockito.times(ResourceData.ONE_TIME)).findOneByMailPass(ResourceData.userInstance);
        Assert.assertNotNull(actualUser);
        Assert.assertEquals(actualUser, ResourceData.userInstance);
    }

    @Test
    public void shouldCheckUserDuplicatesAndCreateNewEntityWhenMailIsUnique() throws PersistException, ServiceException, BusinessException {
        Mockito.when(passwordEncoder.encryptMd5WithPostfixSalt(Mockito.anyString(), Mockito.anyString())).thenReturn(ResourceData.SAMPLE_STRING);
        Mockito.when(userDao.findOneByMail(ResourceData.userInstance)).thenReturn(null);
        underTest.registration(ResourceData.userInstance);

        Mockito.verify(userDao, Mockito.times(ResourceData.ONE_TIME)).findOneByMail(Mockito.any(User.class));
        Mockito.verify(userDao, Mockito.times(ResourceData.ONE_TIME)).create(Mockito.any(User.class));
    }

    @Test(expected = BusinessException.class)
    public void shouldCheckUserDuplicatesAndThrowNewBusinessExceptionWhenMailIsNotUnique() throws PersistException, ServiceException, BusinessException {
        Mockito.when(passwordEncoder.encryptMd5WithPostfixSalt(Mockito.anyString(), Mockito.anyString())).thenReturn(ResourceData.SAMPLE_STRING);
        Mockito.when(userDao.findOneByMail(ResourceData.userInstance)).thenReturn(ResourceData.userInstance);
        underTest.registration(ResourceData.userInstance);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionWhenUserAlreadyExists() throws PersistException, ServiceException, BusinessException {
        Mockito.when(passwordEncoder.encryptMd5WithPostfixSalt(Mockito.anyString(), Mockito.anyString())).thenReturn(ResourceData.SAMPLE_STRING);
        Mockito.when(userDao.findOneByMail(ResourceData.userInstance)).thenThrow(PersistException.class);
        underTest.registration(ResourceData.userInstance);
    }

    @Test
    public void shouldDeleteUserWhenUserValid() throws PersistException, ServiceException {
        underTest.delete(ResourceData.userInstance);
        Mockito.verify(userDao, Mockito.times(ResourceData.ONE_TIME)).delete(Mockito.any(User.class));
    }

    @Test
    public void shouldReturnOneUserWhenUserIdValid() throws PersistException, ServiceException {
        Mockito.when(userDao.findOne(Mockito.anyLong())).thenReturn(ResourceData.userInstance);
        User actualUser = underTest.findOneById(ResourceData.ENTITY_ID_1);
        Mockito.verify(userDao, Mockito.times(ResourceData.ONE_TIME)).findOne(ResourceData.ENTITY_ID_1);
        Long actualUserId = actualUser.getId();
        Assert.assertEquals(actualUserId, ResourceData.userInstance.getId());
        Assert.assertEquals(actualUser, ResourceData.userInstance);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionWhenUserValid() throws PersistException, ServiceException {
        Mockito.when(userDao.findOne(Mockito.anyLong())).thenThrow(PersistException.class);
        underTest.findOneById(ResourceData.ENTITY_ID_1);
    }

    @Test
    public void shouldReturnEntityList() throws PersistException, ServiceException {
        List<User> expectedList = new ArrayList<>();
        Mockito.doReturn(expectedList).when(userDao).findAll();
        List<User> currentList = underTest.findAll();

        Assert.assertEquals(expectedList, currentList);
    }

    @Test
    public void shouldUpdateUserWhenUserValid() throws PersistException, ServiceException, BusinessException {
        underTest.update(ResourceData.userInstance);
        Mockito.verify(userDao, Mockito.times(ResourceData.ONE_TIME)).update(ResourceData.userInstance);
    }
}
