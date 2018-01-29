package com.epam.training.library.viewlayer.command;

import com.epam.training.library.viewlayer.command.exception.ActionException;
import com.epam.training.library.daolayer.model.Book;
import com.epam.training.library.daolayer.model.Receipt;
import com.epam.training.library.daolayer.model.User;
import com.epam.training.library.daolayer.service.ReceiptService;
import com.epam.training.library.daolayer.service.exception.ServiceException;
import com.epam.training.library.viewlayer.util.RequestManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BookOrderCommand extends AbstractActionCommand {
    private ReceiptService receiptService;

    public BookOrderCommand(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        Receipt receipt = parseRequestData(request);
        try {
            receiptService.save(receipt);
            HttpSession session = request.getSession(false);
            session.setAttribute(Message.SUCCESS.toString(), Message.BOOK_REQUEST_SENT.toString());
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
