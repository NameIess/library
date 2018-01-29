package com.epam.training.library.daolayer.service.impl;

import com.epam.training.library.daolayer.dao.BookDao;
import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resources.ResourceData;
import com.epam.training.library.daolayer.service.BookService;
import com.epam.training.library.daolayer.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    private BookService underTest;
    private BookDao bookDao;

    @Before
    public void doSetup() {
        bookDao = mock(BookDao.class);
        underTest = new BookServiceImpl(bookDao);
    }

    @Test
    public void shouldSaveBookWhenBookValid() throws PersistException, ServiceException {
        underTest.save(ResourceData.bookInstance);
        verify(bookDao, times(ResourceData.ONE_TIME)).create(any(Book.class));
    }


    @Test
    public void shouldDeleteBookWhenBookValid() throws PersistException, ServiceException {
        underTest.delete(ResourceData.bookInstance);
        verify(bookDao, times(ResourceData.ONE_TIME)).delete(any(Book.class));
    }


    @Test
    public void shouldReturnOneBookWhenBookIdValid() throws PersistException, ServiceException {
        when(bookDao.findOne(anyLong())).thenReturn(ResourceData.bookInstance);
        Book actualBook = underTest.findOneById(ResourceData.ENTITY_ID_1);
        verify(bookDao, times(ResourceData.ONE_TIME)).findOne(ResourceData.ENTITY_ID_1);
        Long actualBookId = actualBook.getId();
        Long expectedBookId = ResourceData.bookInstance.getId();

        Assert.assertEquals(actualBookId, expectedBookId);
        Assert.assertEquals(actualBook, ResourceData.bookInstance);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionWhenBookInvalid() throws PersistException, ServiceException {
        when(bookDao.findOne(ResourceData.ENTITY_ID_1)).thenThrow(PersistException.class);
        underTest.findOneById(ResourceData.ENTITY_ID_1);
    }


    @Test
    public void shouldReturnBookListWhenDataValid() throws PersistException, ServiceException {
        List<Book> expectedList = new ArrayList<>();
        doReturn(expectedList).when(bookDao).findAll();
        List<Book> currentList = underTest.findAll();

        verify(bookDao, times(ResourceData.ONE_TIME)).findAll();
        Assert.assertEquals(expectedList, currentList);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionWhenBooksInvalid() throws PersistException, ServiceException {
        when(bookDao.findAll()).thenThrow(PersistException.class);
        underTest.findAll();
    }
}
