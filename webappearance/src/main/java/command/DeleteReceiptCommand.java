package command;

import command.exception.ActionException;
import model.Receipt;
import service.ReceiptService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteReceiptCommand extends AbstractActionCommand {
    private ReceiptService receiptService;

    public DeleteReceiptCommand(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        Receipt receipt = parseRequestData(request);
        try {
            receiptService.delete(receipt);
            HttpSession session = request.getSession(false);
            session.setAttribute(Message.SUCCESS.toString(), Message.RECEIPT_DELETED.toString());
        } catch (ServiceException e) {
            throw new ActionException("Error within DeleteReceiptCommand execute(): " + e.getMessage(), e);
        }

        String path = buildPathMap(Page.REDIRECT, Page.RESULT);
        return path;
    }

    private Receipt parseRequestData(HttpServletRequest request) {
        String idParameter = request.getParameter(Receipt.ID_ALIAS);
        Long id = Long.valueOf(idParameter);
        Receipt receipt = new Receipt();
        receipt.setId(id);
        return receipt;
    }

}
