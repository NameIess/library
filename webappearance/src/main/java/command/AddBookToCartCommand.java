package command;

import command.exception.ActionException;
import model.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.BookService;
import service.exception.ServiceException;

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
