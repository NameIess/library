package com.epam.training.library.daolayer.service.impl;

import com.epam.training.library.daolayer.connection.ConnectionManager;
import com.epam.training.library.daolayer.dao.RoleDao;
import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.Role;
import com.epam.training.library.daolayer.service.RoleService;
import com.epam.training.library.daolayer.service.exception.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import resources.ResourceData;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;


public class RoleServiceImplTest {
    private RoleService underTest;
    private RoleDao roleDao;


    @Before
    public void doSetup() {
        roleDao = Mockito.mock(RoleDao.class);
        underTest = new RoleServiceImpl(roleDao);
        ConnectionManager connectionManager = Mockito.mock(ConnectionManager.class);
        Mockito.when(roleDao.getConnectionManager()).thenReturn(connectionManager);
    }

    @Test
    public void shouldReturnRoleListWhenDataValid() throws PersistException, ServiceException {
        List<Role> expectedList = new ArrayList<>();
        Mockito.doReturn(expectedList).when(roleDao).findAll();
        List<Role> currentList = underTest.findAll();

        Mockito.verify(roleDao, Mockito.times(ResourceData.ONE_TIME)).findAll();
        Assert.assertEquals(expectedList, currentList);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionWhenDataInvalid() throws PersistException, ServiceException {
        Mockito.when(roleDao.findAll()).thenThrow(PersistException.class);
        underTest.findAll();
    }

    @Test
    public void shouldReturnOneRoleWhenRoleIdValid() throws PersistException, ServiceException {
        when(roleDao.findOne(anyLong())).thenReturn(ResourceData.roleInstance);
        Role actualRole = underTest.findOneById(ResourceData.ENTITY_ID_1);
        verify(roleDao, times(ResourceData.ONE_TIME)).findOne(ResourceData.ENTITY_ID_1);
        Long actualRoleId = actualRole.getId();
        Long expectedRoleId = ResourceData.roleInstance.getId();

        Assert.assertEquals(actualRoleId, expectedRoleId);
        Assert.assertEquals(actualRole, ResourceData.roleInstance);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionWhenDataOccurs() throws PersistException, ServiceException {
        Mockito.when(roleDao.findOne(Mockito.anyLong())).thenThrow(PersistException.class);
        underTest.findOneById(ResourceData.ENTITY_ID_1);
    }
}
