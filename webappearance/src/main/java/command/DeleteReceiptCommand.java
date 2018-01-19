package command;

import command.exception.ActionException;
import model.Receipt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ReceiptService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class DeleteReceiptCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(DeleteReceiptCommand.class);
    private ReceiptService receiptService;

    public DeleteReceiptCommand(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        Receipt receipt = parseRequestData(request);
        try {
            receiptService.delete(receipt);
        } catch (ServiceException e) {
            throw new ActionException("Error within DeleteReceiptCommand execute(): " + e.getMessage(), e);

        }

        String path = getHomePageByRole(request);
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
