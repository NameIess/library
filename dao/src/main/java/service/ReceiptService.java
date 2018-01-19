package service;

import model.Receipt;
import model.User;
import service.exception.ServiceException;

import java.util.List;

public interface ReceiptService {
    void save(Receipt receipt) throws ServiceException;

    void delete(Receipt receipt) throws ServiceException;

    Receipt findOneById(Long id) throws ServiceException;

    List<Receipt> findAll() throws ServiceException;

    List<Receipt> findAllByUserId(User user) throws ServiceException;

    void transferBook(Long receiptId, Long statusId, Long bookId, Integer orderedBookQuantity, Integer availableBookQuantity) throws ServiceException;
}
