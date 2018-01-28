package command;

import command.exception.ActionException;
import model.Receipt;
import model.User;
import service.ReceiptService;
import service.exception.ServiceException;
import util.RequestManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserReceiptCommand extends AbstractActionCommand {
    private static final String REQUEST_RECEIPTS_ATTRIBUTE = "receipts";
    private ReceiptService receiptService;

    public UserReceiptCommand(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        User user = RequestManager.getUserFromSession(request);
        try {
            List<Receipt> receiptList = receiptService.findAllByUserId(user);
            request.setAttribute(REQUEST_RECEIPTS_ATTRIBUTE, receiptList);
        } catch (ServiceException e) {
            throw new ActionException("Error within UserReceiptCommand execute(): " + e.getMessage(), e);
        }

        String path = buildPathMap(Page.FORWARD, Page.USER_RECEIPT);
        return path;
    }
}
