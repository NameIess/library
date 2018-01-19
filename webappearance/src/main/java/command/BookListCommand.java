package command;

import command.exception.ActionException;
import model.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.BookService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BookListCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(BookListCommand.class.getSimpleName());
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
