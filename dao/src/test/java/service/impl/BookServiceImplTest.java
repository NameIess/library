package service.impl;

import dao.BookDao;
import dao.exception.PersistException;
import model.Book;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resources.ResourceData;
import service.BookService;
import service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    private BookService bookService;
    private BookDao bookDao;

    @Before
    public void doSetup() {
        bookDao = mock(BookDao.class);
        bookService = new BookServiceImpl(bookDao);
    }

    @Test
    public void shouldSaveBookWhenBookValid() throws PersistException, ServiceException {
        bookService.save(ResourceData.book);
        verify(bookDao, times(ResourceData.NUMBER_1)).create(any(Book.class));
    }


    @Test
    public void shouldDeleteBookWhenBookValid() throws PersistException, ServiceException {
        bookService.delete(ResourceData.book);
        verify(bookDao, times(ResourceData.NUMBER_1)).delete(any(Book.class));
    }


    @Test
    public void shouldReturnOneBookWhenBookIdValid() throws PersistException, ServiceException {
        when(bookDao.findOne(anyLong())).thenReturn(ResourceData.book);
        Book actualBook = bookService.findOneById(ResourceData.ENTITY_ID_1);
        verify(bookDao, times(ResourceData.NUMBER_1)).findOne(ResourceData.ENTITY_ID_1);
        Long actualBookId = ResourceData.book.getId();

        Assert.assertEquals(actualBookId, ResourceData.ENTITY_ID_1);
        Assert.assertEquals(actualBook, ResourceData.book);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionWhenBookInvalid() throws PersistException, ServiceException {
        when(bookDao.findOne(ResourceData.ENTITY_ID_1)).thenThrow(ServiceException.class);
        bookService.findOneById(ResourceData.ENTITY_ID_1);
    }


    @Test
    public void shouldReturnBookList() throws PersistException, ServiceException {
        List<Book> expectedList = new ArrayList<>();
        doReturn(expectedList).when(bookDao).findAll();
        List<Book> currentList = bookService.findAll();

        verify(bookDao, times(ResourceData.NUMBER_1)).findAll();
        Assert.assertEquals(expectedList, currentList);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionWhenBooksInvalid() throws PersistException, ServiceException {
        when(bookDao.findAll()).thenThrow(ServiceException.class);
        bookService.findAll();
    }
}
