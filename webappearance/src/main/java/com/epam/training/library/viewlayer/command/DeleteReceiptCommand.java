package com.epam.training.library.viewlayer.command;

import com.epam.training.library.viewlayer.command.exception.ActionException;
import com.epam.training.library.daolayer.model.Receipt;
import com.epam.training.library.daolayer.service.ReceiptService;
import com.epam.training.library.daolayer.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteReceiptCommand extends AbstractActionCommand {
    private ReceiptService receiptService;

    public DeleteReceiptCommand(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        Long receiptId = parseRequestData(request);
        try {
            receiptService.delete(receiptId);
            HttpSession session = request.getSession(false);
            session.setAttribute(Message.SUCCESS.toString(), Message.RECEIPT_DELETED.toString());
        } catch (ServiceException e) {
            throw new ActionException("Error within DeleteReceiptCommand execute(): " + e.getMessage(), e);
        }

        String path = buildPathMap(Page.REDIRECT, Page.RESULT);
        return path;
    }

    private Long parseRequestData(HttpServletRequest request) {
        String idParameter = request.getParameter(Receipt.ID_ALIAS);
        Long id = Long.valueOf(idParameter);
        return id;
    }

}
