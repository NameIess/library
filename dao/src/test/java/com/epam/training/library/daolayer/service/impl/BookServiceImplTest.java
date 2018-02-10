package com.epam.training.library.daolayer.service.impl;

import com.epam.training.library.daolayer.connection.ConnectionManager;
import com.epam.training.library.daolayer.dao.BookDao;
import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.Book;
import com.epam.training.library.daolayer.service.BookService;
import com.epam.training.library.daolayer.service.exception.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import resources.ResourceData;

import java.util.ArrayList;
import java.util.List;


public class BookServiceImplTest {

    private BookService underTest;
    private BookDao bookDao;

    @Before
    public void doSetup() {
        bookDao = Mockito.mock(BookDao.class);
        underTest = new BookServiceImpl(bookDao);
        ConnectionManager connectionManager = Mockito.mock(ConnectionManager.class);
        Mockito.when(bookDao.getConnectionManager()).thenReturn(connectionManager);
    }

    @Test
    public void shouldReturnOneBookWhenBookIdValid() throws PersistException, ServiceException {
        Mockito.when(bookDao.findOne(Mockito.anyLong())).thenReturn(ResourceData.bookInstance);
        Book actualBook = underTest.findOneById(ResourceData.ENTITY_ID_1);
        Mockito.verify(bookDao, Mockito.times(ResourceData.ONE_TIME)).findOne(ResourceData.ENTITY_ID_1);
        Long actualBookId = actualBook.getId();
        Long expectedBookId = ResourceData.bookInstance.getId();

        Assert.assertEquals(actualBookId, expectedBookId);
        Assert.assertEquals(actualBook, ResourceData.bookInstance);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionWhenBookInvalid() throws PersistException, ServiceException {
        Mockito.when(bookDao.findOne(ResourceData.ENTITY_ID_1)).thenThrow(PersistException.class);
        underTest.findOneById(ResourceData.ENTITY_ID_1);
    }


    @Test
    public void shouldReturnBookListWhenDataValid() throws PersistException, ServiceException {
        List<Book> expectedList = new ArrayList<>();
        Mockito.doReturn(expectedList).when(bookDao).findAll();
        List<Book> currentList = underTest.findAll();

        Mockito.verify(bookDao, Mockito.times(ResourceData.ONE_TIME)).findAll();
        Assert.assertEquals(expectedList, currentList);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionWhenBooksInvalid() throws PersistException, ServiceException {
        Mockito.when(bookDao.findAll()).thenThrow(PersistException.class);
        underTest.findAll();
    }

    @Test
    public void shouldNotUpdateBookWhenBookWasNotChanged() throws PersistException, ServiceException {
        Mockito.when(bookDao.findOne(Mockito.anyLong())).thenReturn(ResourceData.bookInstance);
        underTest.update(ResourceData.bookInstance);
        Mockito.verify(bookDao, Mockito.times(ResourceData.ZERO_INTEGER_VALUE)).update(ResourceData.bookInstance);
        Assert.assertEquals(ResourceData.bookInstance, ResourceData.bookInstance);
    }

    @Test
    public void shouldUpdateBookWhenBookWasChanged() throws PersistException, ServiceException {
        Book updatedBook = new Book();
        updatedBook.setId(ResourceData.ENTITY_ID_1);
        updatedBook.setAuthor(ResourceData.SAMPLE_STRING);
        Mockito.when(bookDao.findOne(Mockito.anyLong())).thenReturn(ResourceData.bookInstance);
        underTest.update(updatedBook);
        Mockito.verify(bookDao, Mockito.times(ResourceData.ONE_TIME)).update(updatedBook);
        Assert.assertNotEquals(updatedBook, ResourceData.bookInstance);
    }
}
