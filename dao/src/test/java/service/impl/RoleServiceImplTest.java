package service.impl;

import dao.RoleDao;
import dao.exception.PersistException;
import model.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import resources.ResourceData;
import service.RoleService;
import service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;


public class RoleServiceImplTest {
    private RoleService underTest;
    private RoleDao roleDao;


    @Before
    public void doSetup() {
        roleDao = Mockito.mock(RoleDao.class);
        underTest = new RoleServiceImpl(roleDao);
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
    public void shouldDeleteRoleWhenRoleValid() throws PersistException, ServiceException {
        underTest.delete(ResourceData.roleInstance);
        Mockito.verify(roleDao, Mockito.times(ResourceData.ONE_TIME)).delete(Mockito.any(Role.class));
    }

    @Test
    public void shouldReturnOneRoleWhenRoleIdValid() throws PersistException, ServiceException {
        Mockito.when(roleDao.findOne(Mockito.anyLong())).thenReturn(ResourceData.roleInstance);
        Role actualRole = underTest.findOneById(ResourceData.ENTITY_ID_1);
        Mockito.verify(roleDao, Mockito.times(ResourceData.ONE_TIME)).findOne(ResourceData.ENTITY_ID_1);
        Long actualRoleId = actualRole.getId();
        Long expectedRoleId = ResourceData.roleInstance.getId();

        Assert.assertEquals(actualRoleId, expectedRoleId);
        Assert.assertEquals(actualRole, ResourceData.roleInstance);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionWhenRoleIdInvalid() throws PersistException, ServiceException {
        Mockito.when(roleDao.findOne(ResourceData.ENTITY_ID_1)).thenThrow(PersistException.class);
        underTest.findOneById(ResourceData.ENTITY_ID_1);
    }

    @Test
    public void shouldUpdateRoleWhenRoleValid() throws PersistException, ServiceException {
        underTest.update(ResourceData.roleInstance);
        Mockito.verify(roleDao, Mockito.times(ResourceData.ONE_TIME)).update(ResourceData.roleInstance);
    }
}
