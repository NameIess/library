package command;

import command.exception.ActionException;
import model.Book;
import model.Receipt;
import model.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ReceiptService;
import service.exception.BusinessException;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TransferBookCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(TransferBookCommand.class.getSimpleName());
    private static final String ORDERED_QUANTITY_PARAMETER = "quantity";
    private static final String AVAILABLE_BOOK_AMOUNT = "amount";
    private static final String ERROR_TRANSFER_MESSAGE_ATTRIBUTE = "transferError";
    private ReceiptService receiptService;

    public TransferBookCommand(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        String receiptIdParameter = request.getParameter(Receipt.ID_ALIAS);
        Long receiptId = Long.valueOf(receiptIdParameter);

        String statusIdParameter = request.getParameter(Status.FOREIGN_KEY_ALIAS);
        Long statusId = Long.valueOf(statusIdParameter);

        String bookIdParameter = request.getParameter(Book.FOREIGN_KEY_ALIAS);
        Long bookId = Long.valueOf(bookIdParameter);

        String orderedBookQuantityParameter = request.getParameter(ORDERED_QUANTITY_PARAMETER);
        Integer orderedBookQuantity = Integer.valueOf(orderedBookQuantityParameter);

        String availableBookAmountParameter = request.getParameter(AVAILABLE_BOOK_AMOUNT);
        Integer availableBookQuantity = Integer.valueOf(availableBookAmountParameter);

        String path = buildPathMap(Page.REDIRECT, Page.RESULT);
        try {
            receiptService.transferBook(receiptId, statusId, bookId, orderedBookQuantity, availableBookQuantity);
            HttpSession session = request.getSession(false);
            session.setAttribute(Message.SUCCESS.toString(), Message.BOOK_TRANSFERRED_SUCCESSFULLY.toString());
        } catch (ServiceException e) {
            throw new ActionException("Error within TransferBookCommand execute(): " + e.getMessage(), e);
        } catch (BusinessException e) {
            Log.error("Transfer error: " + e.getMessage());
            request.setAttribute(ERROR_TRANSFER_MESSAGE_ATTRIBUTE, Message.INVALID_ORDERED_ITEMS_AMOUNT.toString());
            path = buildPathMap(Page.FORWARD, Page.RESULT);
        }

        return path;
    }
}
