package com.epam.training.library.daolayer.service;

import com.epam.training.library.daolayer.model.Receipt;
import com.epam.training.library.daolayer.model.User;
import com.epam.training.library.daolayer.service.exception.BusinessException;
import com.epam.training.library.daolayer.service.exception.ServiceException;

import java.util.List;

/**
 * Contains the basic operations interaction with the receipts.
 */
public interface ReceiptService extends Service {

    /**
     * Saves the specified receipt
     * @param receipt — receipt that must be saved
     * @throws ServiceException when data access occurs
     */
    void save(Receipt receipt) throws ServiceException;

    /**
     * Deletes a particular receipt with the specified id
     * @param receiptId — a receipt identifier that is used to find and delete a required instance
     * @throws ServiceException when data access occurs
     */
    void delete(Long receiptId) throws ServiceException;

    /**
     * Returns a list of all receipts
     * @return a list of all receipts
     * @throws ServiceException when data access occurs
     */
    List<Receipt> findAll() throws ServiceException;

    /**
     * Returns a list of all receipts, submitted by specified user
     * @param id — user who wants get access to their receipts
     * @return a list of receipts submitted by specified user
     * @throws ServiceException when data access occurs
     */
    List<Receipt> findAllByUserId(Long id) throws ServiceException;

    /**
     * Updates the receipt status, updates the number of books available in stock
     * @param receiptId — receipt identifier on that the operation is performed
     * @param statusId — current status identifier
     * @param bookId — ordered book
     * @param orderedBookQuantity — number of books ordered by the user
     * @param availableBookQuantity — number of books available in stock
     * @throws ServiceException when data access occurs
     * @throws BusinessException when the orderedBookQuantity is more than availableBookQuantity
     */
    void transferBook(Long receiptId, Long statusId, Long bookId, Integer orderedBookQuantity, Integer availableBookQuantity) throws ServiceException, BusinessException;
}
