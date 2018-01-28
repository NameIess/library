package service.impl;

import dao.BookDao;
import dao.ReceiptDao;
import dao.exception.PersistException;
import model.Book;
import model.Receipt;
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
import service.ReceiptService;
import service.exception.BusinessException;
import service.exception.ServiceException;

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
        underTest.delete(ResourceData.receiptInstance);
        Mockito.verify(receiptDao, Mockito.times(ResourceData.ONE_TIME)).delete(Mockito.any(Receipt.class));
    }

    @Test
    public void shouldReturnOneReceiptWhenReceiptIdValid() throws PersistException, ServiceException {
        Mockito.when(receiptDao.findOne(Mockito.anyLong())).thenReturn(ResourceData.receiptInstance);
        Receipt actualReceipt = underTest.findOneById(ResourceData.ENTITY_ID_1);
        Mockito.verify(receiptDao, Mockito.times(ResourceData.ONE_TIME)).findOne(Mockito.anyLong());
        Long actualBookId = actualReceipt.getId();
        Long expectedBookId = ResourceData.receiptInstance.getId();

        Assert.assertEquals(actualBookId, expectedBookId);
        Assert.assertEquals(actualReceipt, ResourceData.receiptInstance);
    }

    @Test(expected = ServiceException.class)
    public void shoulThrowServiceExceptionWhenReceiptInvalid() throws PersistException, ServiceException {
        Mockito.when(receiptDao.findOne(Mockito.anyLong())).thenThrow(PersistException.class);
        underTest.findOneById(ResourceData.ENTITY_ID_1);
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
        List<Receipt> actualList = underTest.findAllByUserId(ResourceData.userInstance);

        Assert.assertTrue(!actualList.isEmpty() && !expectedList.isEmpty());
        Assert.assertTrue(actualList.size() == expectedList.size());
        Assert.assertEquals(actualList, expectedList);
    }

    @Test(expected = ServiceException.class)
    public void shoulThrowServiceExceptionWhenUserInvalid() throws PersistException, ServiceException {
        Mockito.when(receiptDao.findAllByUserId(Mockito.anyLong())).thenThrow(PersistException.class);
        underTest.findAllByUserId(ResourceData.userInstance);
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
