package com.epam.training.library.viewlayer.command;

import com.epam.training.library.viewlayer.command.exception.ActionException;
import com.epam.training.library.daolayer.model.Receipt;
import com.epam.training.library.daolayer.model.User;
import com.epam.training.library.daolayer.service.ReceiptService;
import com.epam.training.library.daolayer.service.exception.ServiceException;
import com.epam.training.library.viewlayer.util.RequestManager;

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
