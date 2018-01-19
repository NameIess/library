package command;

import command.exception.ActionException;
import model.Receipt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ReceiptService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ReceiptListCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(ReceiptListCommand.class.getSimpleName());
    private static final String REQUEST_RECEIPTS_ATTRIBUTE = "receipts";
    private ReceiptService receiptService;

    public ReceiptListCommand(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        try {
            List<Receipt> receiptList = receiptService.findAll();
            request.setAttribute(REQUEST_RECEIPTS_ATTRIBUTE, receiptList);
        } catch (ServiceException e) {
            throw new ActionException("Error within ReceiptListCommand execute(): " + e.getMessage(), e);
        }

        String path = buildPathMap(Page.FORWARD, Page.RECEIPT_LIST);
        return path;
    }
}
