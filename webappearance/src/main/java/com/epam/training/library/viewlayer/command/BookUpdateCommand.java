package com.epam.training.library.viewlayer.command;

import com.epam.training.library.daolayer.model.Book;
import com.epam.training.library.daolayer.service.BookService;
import com.epam.training.library.daolayer.service.exception.ServiceException;
import com.epam.training.library.daolayer.service.impl.BookServiceImpl;
import com.epam.training.library.viewlayer.command.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BookUpdateCommand extends AbstractActionCommand {
    private BookService bookService;

    public BookUpdateCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        Book book = parseRequestData(request);
        try {
            bookService.update(book);
            HttpSession session = request.getSession(false);
            session.setAttribute(Message.SUCCESS.toString(), Message.BOOK_UPDATED_SUCCESSFULLY.toString());
        } catch (ServiceException e) {
            throw new ActionException("Error within BookUpdateCommand execute(): " + e.getMessage(), e);
        }
        String path = buildPathMap(Page.REDIRECT, Page.RESULT);

        return path;
    }

    private Book parseRequestData(HttpServletRequest request) {
        Book book = new Book();
        String bookIdParameter = request.getParameter(Book.FOREIGN_KEY_ALIAS);
        Long bookId = Long.valueOf(bookIdParameter);
        book.setId(bookId);
        String title = request.getParameter(Book.TITLE_ALIAS);
        book.setTitle(title);
        String author = request.getParameter(Book.AUTHOR_ALIAS);
        book.setAuthor(author);
        String yearOfPublishingParameter = request.getParameter(Book.YEAR_OF_PUBLISHING_ALIAS);
        Short yearOfPublishing = Short.valueOf(yearOfPublishingParameter);
        book.setYearOfPublishing(yearOfPublishing);
        String numberOfPagesParameter = request.getParameter(Book.NUMBER_OF_PAGES_ALIAS);
        Short numberOfPages = Short.valueOf(numberOfPagesParameter);
        book.setNumberOfPages(numberOfPages);
        String description = request.getParameter(Book.DESCRIPTION_ALIAS);
        book.setDescription(description);
        String amountParameter = request.getParameter(Book.AMOUNT_ALIAS);
        Integer amount = Integer.valueOf(amountParameter);
        book.setAmount(amount);

        return book;
    }
}
