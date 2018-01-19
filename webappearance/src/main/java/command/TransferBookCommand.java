package command;

import command.exception.ActionException;
import model.Book;
import model.Receipt;
import model.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ReceiptService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class TransferBookCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(TransferBookCommand.class.getSimpleName());
    private static final String ORDERED_QUANTITY_PARAMETER = "quantity";
    private static final String AVAILABLE_BOOK_AMOUNT = "amount";
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

        try {
            receiptService.transferBook(receiptId, statusId, bookId, orderedBookQuantity, availableBookQuantity);
        } catch (ServiceException e) {
            throw new ActionException("Error within TransferBookCommand execute(): " + e.getMessage(), e);
        }

        String path = getHomePageByRole(request);
        return path;
    }

//    private int calculateTransferAmount(int orderedBookQuantity, int availableBookQuantity, Long statusId) {
//        int updatedAmount = 0;
//        if (statusId == Status.STATUS_TRANSFERRED_ID) {
//            updatedAmount = availableBookQuantity - orderedBookQuantity;
//        } else if (statusId == Status.STATUS_REJECTED_ID) {
//            updatedAmount = availableBookQuantity;
//        } else if (statusId == Status.STATUS_RETURNED_ID) {
//            updatedAmount = availableBookQuantity + orderedBookQuantity;
//        }
//
//        return updatedAmount;
//    }
}
