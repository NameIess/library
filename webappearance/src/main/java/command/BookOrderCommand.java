package command;

import command.exception.ActionException;
import model.Book;
import model.Receipt;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ReceiptService;
import service.exception.ServiceException;
import util.RequestManager;

import javax.servlet.http.HttpServletRequest;

public class BookOrderCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(BookOrderCommand.class.getSimpleName());
    private ReceiptService receiptService;

    public BookOrderCommand(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        Receipt receipt = parseRequestData(request);
        Log.info("Receipt before saving: " + receipt);
        try {
            receiptService.save(receipt);
        } catch (ServiceException e) {
            throw new ActionException("Error within BookOrderCommand execute(): " + e.getMessage(), e);
        }

        String path = buildPathMap(Page.REDIRECT, Page.RESULT);
        return path;
    }

    private Receipt parseRequestData(HttpServletRequest request) {
        Receipt receipt = new Receipt();

        String idParameter = request.getParameter(Book.ID_ALIAS);
        Long id = Long.valueOf(idParameter);

        Book book = new Book();
        book.setId(id);
        receipt.setBook(book);

        User user = RequestManager.getUserFromSession(request);
        receipt.setUser(user);

        String quantityParameter = request.getParameter(Receipt.QUANTITY_ALIAS);
        Integer quantity = Integer.valueOf(quantityParameter);
        receipt.setQuantity(quantity);

        String subscriptionParameter = request.getParameter(Receipt.IS_SUBSCRIPTION_ALIAS);
        Boolean subscription = Boolean.parseBoolean(subscriptionParameter);
        receipt.setSubscription(subscription);

        String termParameter = request.getParameter(Receipt.TERM_ALIAS);
        receipt.setTerm(termParameter);
        return receipt;
    }
}
