package com.epam.training.library.viewlayer.command;

import com.epam.training.library.viewlayer.command.exception.ActionException;
import com.epam.training.library.daolayer.model.Book;
import com.epam.training.library.daolayer.service.BookService;
import com.epam.training.library.daolayer.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BookListCommand extends AbstractActionCommand {
    private static final String REQUEST_BOOKS_ATTRIBUTE = "books";
    private BookService bookService;

    public BookListCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        try {
            List<Book> bookList = bookService.findAll();
            request.setAttribute(REQUEST_BOOKS_ATTRIBUTE, bookList);
        } catch (ServiceException e) {
            throw new ActionException("Error within BookListCommand execute(): " + e.getMessage(), e);
        }

        String path = buildPathMap(Page.FORWARD, Page.LIBRARY_HOME);
        return path;
    }
}
