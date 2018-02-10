package com.epam.training.library.viewlayer.command;

import com.epam.training.library.daolayer.model.Book;
import com.epam.training.library.daolayer.service.BookService;
import com.epam.training.library.daolayer.service.exception.ServiceException;
import com.epam.training.library.daolayer.service.impl.BookServiceImpl;
import com.epam.training.library.viewlayer.command.exception.ActionException;

import javax.servlet.http.HttpServletRequest;

public class BookPrepareUpdateCommand extends AbstractActionCommand {
    private static final String REQUEST_EDIT_BOOK_ATTRIBUTE = "edit_book";
    private BookService bookService;

    public BookPrepareUpdateCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        String bookIdParameter = request.getParameter(Book.FOREIGN_KEY_ALIAS);
        Long bookId = Long.valueOf(bookIdParameter);
        try {
            Book book = bookService.findOneById(bookId);
            request.setAttribute(REQUEST_EDIT_BOOK_ATTRIBUTE, book);
        } catch (ServiceException e) {
            throw new ActionException("Error within BookPrepareUpdateCommand execute(): " + e.getMessage(), e);
        }
        String path = buildPathMap(Page.FORWARD, Page.BOOK_REGISTRATION);

        return path;
    }
}
