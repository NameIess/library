package com.epam.training.library.viewlayer.command;

import com.epam.training.library.viewlayer.command.exception.ActionException;
import com.epam.training.library.daolayer.model.Book;
import com.epam.training.library.daolayer.service.BookService;
import com.epam.training.library.daolayer.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class AddBookToCartCommand extends AbstractActionCommand {
    private BookService bookService;

    public AddBookToCartCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        String idParameter = request.getParameter(Book.ID_ALIAS);
        Long id = Long.valueOf(idParameter);

        try {
            Book book = bookService.findOneById(id);
            request.setAttribute(Book.TABLE_NAME, book);
        } catch (ServiceException e) {
            throw new ActionException("Error within AddBookToCartCommand execute(): " + e.getMessage(), e);
        }

        String path = buildPathMap(Page.FORWARD, Page.CART);
        return path;
    }
}
