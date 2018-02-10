package com.epam.training.library.daolayer.service.impl;

import com.epam.training.library.daolayer.connection.ConnectionManager;
import com.epam.training.library.daolayer.dao.BookDao;
import com.epam.training.library.daolayer.dao.ReceiptDao;
import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.Book;
import com.epam.training.library.daolayer.model.Receipt;
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
import com.epam.training.library.daolayer.service.ReceiptService;
import com.epam.training.library.daolayer.service.exception.BusinessException;
import com.epam.training.library.daolayer.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ReceiptServiceImpl.class)
@PowerMockIgnore("javax.management.*")
public class ReceiptServiceImplTest {
    private ReceiptDao receiptDao;
    private BookDao bookDao;
    private ReceiptService underTest;

    @Before
    public void doSetup() {
        bookDao = Mockito.mock(BookDao.class);
        receiptDao = Mockito.mock(ReceiptDao.class);
        underTest = new ReceiptServiceImpl(receiptDao, bookDao);
        ConnectionManager connectionManager = Mockito.mock(ConnectionManager.class);
        Mockito.when(bookDao.getConnectionManager()).thenReturn(connectionManager);
        Mockito.when(receiptDao.getConnectionManager()).thenReturn(connectionManager);
    }

    @Test(expected = BusinessException.class)
    public void shouldThrowBusinessExceptionWhenOrderedAmountGreaterThanAvailable() throws Exception {
        ReceiptService spyUnderTest = PowerMockito.spy(underTest);
        PowerMockito.doReturn(ResourceData.NEGATIVE_INTEGER_VALUE).when(spyUnderTest, ResourceData.CALCULATE_TRANSFER_AMOUNT_METHOD_NAME, Mockito.anyInt(), Mockito.anyInt(), Mockito.anyLong());
        Mockito.when(receiptDao.findOne(Mockito.anyLong())).thenReturn(ResourceData.receiptInstance);
        spyUnderTest.transferBook(ResourceData.ENTITY_ID_1, ResourceData.STATUS_ID, ResourceData.ENTITY_ID_1, ResourceData.ORDERED_BOOKS_AMOUNT, ResourceData.AVAILABLE_BOOKS_AMOUNT);
    }

    @Test
    public void shouldSaveReceiptWhenReceiptValid() throws PersistException, ServiceException {
        underTest.save(ResourceData.receiptInstance);
        Mockito.verify(receiptDao, Mockito.times(ResourceData.ONE_TIME)).create(Mockito.any(Receipt.class));
    }


    @Test
    public void shouldDeleteReceiptWhenReceiptValid() throws PersistException, ServiceException {
        underTest.delete(ResourceData.ENTITY_ID_1);
        Mockito.verify(receiptDao, Mockito.times(ResourceData.ONE_TIME)).delete(Mockito.anyLong());
    }

    @Test
    public void shouldReturnReceiptList() throws PersistException, ServiceException {
        List<Receipt> expectedList = new ArrayList<>();
        Mockito.doReturn(expectedList).when(receiptDao).findAll();
        List<Receipt> currentList = underTest.findAll();

        Assert.assertEquals(expectedList, currentList);
    }

    @Test(expected = ServiceException.class)
    public void shoulThrowServiceExceptionWhenReceiptListInvalid() throws PersistException, ServiceException {
        Mockito.when(receiptDao.findAll()).thenThrow(PersistException.class);
        underTest.findAll();
    }

    @Test
    public void shouldReturnReceiptListByUserWhenUserValid() throws PersistException, ServiceException {
        List<Receipt> expectedList = Arrays.asList(new Receipt(), new Receipt());
        Mockito.doReturn(expectedList).when(receiptDao).findAllByUserId(ResourceData.ENTITY_ID_1);
        List<Receipt> actualList = underTest.findAllByUserId(ResourceData.ENTITY_ID_1);

        Assert.assertTrue(!actualList.isEmpty() && !expectedList.isEmpty());
        Assert.assertTrue(actualList.size() == expectedList.size());
        Assert.assertEquals(actualList, expectedList);
    }

    @Test(expected = ServiceException.class)
    public void shoulThrowServiceExceptionWhenUserInvalid() throws PersistException, ServiceException {
        Mockito.when(receiptDao.findAllByUserId(Mockito.anyLong())).thenThrow(PersistException.class);
        underTest.findAllByUserId(ResourceData.ENTITY_ID_1);
    }

    @Test
    public void shouldChangeReceiptStatusAndChangeBookAvailableAmountWhenOrderedAmoundLessOrEqualAvailable() throws Exception {
        ReceiptService spyUnderTest = PowerMockito.spy(underTest);
        Mockito.when(receiptDao.findOne(Mockito.anyLong())).thenReturn(ResourceData.receiptInstance);

        PowerMockito.doReturn(ResourceData.POSITIVE_INTEGER_VALUE).when(spyUnderTest, ResourceData.CALCULATE_TRANSFER_AMOUNT_METHOD_NAME, Mockito.anyInt(), Mockito.anyInt(), Mockito.anyLong());
        spyUnderTest.transferBook(ResourceData.ENTITY_ID_1, ResourceData.STATUS_ID, ResourceData.ENTITY_ID_1, ResourceData.ORDERED_BOOKS_AMOUNT, ResourceData.AVAILABLE_BOOKS_AMOUNT);

        PowerMockito.doReturn(ResourceData.ZERO_INTEGER_VALUE).when(spyUnderTest, ResourceData.CALCULATE_TRANSFER_AMOUNT_METHOD_NAME, Mockito.anyInt(), Mockito.anyInt(), Mockito.anyLong());
        spyUnderTest.transferBook(ResourceData.ENTITY_ID_1, ResourceData.STATUS_ID, ResourceData.ENTITY_ID_1, ResourceData.ORDERED_BOOKS_AMOUNT, ResourceData.AVAILABLE_BOOKS_AMOUNT);

        Mockito.verify(receiptDao, Mockito.times(ResourceData.TWO_TIMES)).findOne(ResourceData.ENTITY_ID_1);
        Mockito.verify(receiptDao, Mockito.times(ResourceData.TWO_TIMES)).updateStatusById(ResourceData.receiptInstance);
        Mockito.verify(bookDao, Mockito.times(ResourceData.TWO_TIMES)).updateAmountById(Mockito.any(Book.class));
    }
}
