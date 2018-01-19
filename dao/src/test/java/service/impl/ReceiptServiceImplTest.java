package service.impl;

import dao.BookDao;
import dao.ReceiptDao;
import dao.exception.PersistException;
import model.Book;
import model.Receipt;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resources.ResourceData;
import service.ReceiptService;
import service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ReceiptServiceImplTest {
    private ReceiptDao receiptDao;
    private BookDao bookDao;
    private ReceiptService receiptService;

    @Before
    public void doSetup() {
        bookDao = mock(BookDao.class);
        receiptDao = mock(ReceiptDao.class);
        receiptService = new ReceiptServiceImpl(receiptDao, bookDao);
    }

    @Test
    public void shouldSaveReceiptToDatabaseWhenReceiptValid() throws PersistException, ServiceException {
        receiptService.save(ResourceData.receipt);
        verify(receiptDao, times(ResourceData.NUMBER_1)).create(any(Receipt.class));
    }


    @Test
    public void shouldDeleteReceiptWhenReceiptValid() throws PersistException, ServiceException {
        receiptService.delete(ResourceData.receipt);
        verify(receiptDao, times(ResourceData.NUMBER_1)).delete(any(Receipt.class));
    }

    @Test
    public void shouldReturnOneReceiptWhenReceiptIdValid() throws PersistException, ServiceException {
        when(receiptDao.findOne(anyLong())).thenReturn(ResourceData.receipt);
        Receipt actualReceipt = receiptService.findOneById(ResourceData.ENTITY_ID_1);
        verify(receiptDao, times(ResourceData.NUMBER_1)).findOne(anyLong());
        Long actualId = actualReceipt.getId();
        Assert.assertEquals(actualId, ResourceData.receipt.getId());
        Assert.assertEquals(actualReceipt, ResourceData.receipt);
    }

    @Test(expected = ServiceException.class)
    public void shoulThrowServiceExceptionWhenReceiptInvalid() throws PersistException, ServiceException {
        when(receiptDao.findOne(anyLong())).thenThrow(ServiceException.class);
        receiptService.findOneById(ResourceData.ENTITY_ID_1);
    }

    @Test
    public void shouldReturnReceiptList() throws PersistException, ServiceException {
        List<Receipt> expectedList = new ArrayList<>();
        doReturn(expectedList).when(receiptDao).findAll();
        List<Receipt> currentList = receiptService.findAll();

        Assert.assertEquals(expectedList, currentList);
    }

    @Test(expected = ServiceException.class)
    public void shoulThrowServiceExceptionWhenReceiptListInvalid() throws PersistException, ServiceException {
        when(receiptDao.findAll()).thenThrow(ServiceException.class);
        receiptService.findAll();
    }

    @Test
    public void shouldReturnReceiptListByUserWhenUserValid() throws PersistException, ServiceException {
        List<Receipt> expectedList = Arrays.asList(new Receipt(), new Receipt());
        doReturn(expectedList).when(receiptDao).findAllByUserId(ResourceData.ENTITY_ID_1);
        List<Receipt> actualList = receiptService.findAllByUserId(ResourceData.user);

        Assert.assertTrue(!actualList.isEmpty() && !expectedList.isEmpty());
        Assert.assertTrue(actualList.size() == expectedList.size());
        Assert.assertEquals(actualList, expectedList);
    }

    @Test(expected = ServiceException.class)
    public void shoulThrowServiceExceptionWhenUserInvalid() throws PersistException, ServiceException {
        when(receiptDao.findAllByUserId(anyLong())).thenThrow(ServiceException.class);
        receiptService.findAllByUserId(ResourceData.user);
    }

    @Test
    public void shouldChangeReceiptStatusAndChangeBookAvailableAmountWhenIdValid() throws PersistException, ServiceException {
        when(receiptDao.findOne(anyLong())).thenReturn(ResourceData.receipt);
        receiptService.transferBook(ResourceData.ENTITY_ID_1, ResourceData.STATUS_ID, ResourceData.ENTITY_ID_1, ResourceData.ORDERED_BOOKS_AMOUNT, ResourceData.AVAILABLE_BOOKS_AMOUNT);
        verify(receiptDao, times(ResourceData.NUMBER_1)).findOne(ResourceData.ENTITY_ID_1);
        verify(receiptDao, times(ResourceData.NUMBER_1)).updateStatusById(ResourceData.receipt);
        verify(bookDao, times(ResourceData.NUMBER_1)).updateAmountById(any(Book.class));
    }

}
