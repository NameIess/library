package com.epam.training.library.daolayer.service.impl;

import com.epam.training.library.daolayer.dao.BookDao;
import com.epam.training.library.daolayer.dao.ReceiptDao;
import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.Book;
import com.epam.training.library.daolayer.model.Receipt;
import com.epam.training.library.daolayer.model.Status;
import com.epam.training.library.daolayer.model.User;
import com.epam.training.library.daolayer.service.ReceiptService;
import com.epam.training.library.daolayer.service.exception.BusinessException;
import com.epam.training.library.daolayer.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ReceiptServiceImpl implements ReceiptService {
    private static final Logger Log = LogManager.getLogger(ReceiptServiceImpl.class.getSimpleName());
    private ReceiptDao receiptDao;
    private BookDao bookDao;

    public ReceiptServiceImpl(ReceiptDao receiptDao, BookDao bookDao) {
        this.receiptDao = receiptDao;
        this.bookDao = bookDao;
    }

    @Override
    public void save(Receipt receipt) throws ServiceException {
        try {
            receiptDao.getConnectionManager().startTransaction();
            receiptDao.create(receipt);
            receiptDao.getConnectionManager().commitTransaction();
            Log.info("Receipt " + receipt + " has been saved to DB");
        } catch (PersistException e) {
            receiptDao.getConnectionManager().rollbackTransaction();
            throw new ServiceException("Error within ReceiptServiceImpl save(): " + e.getMessage(), e);
        } finally {
            receiptDao.getConnectionManager().releaseConnection();
        }
    }

    @Override
    public void delete(Long receiptId) throws ServiceException {
        try {
            receiptDao.delete(receiptId);
            Log.info("Receipt with the id: " + receiptId + " has been removed from DB");
        } catch (PersistException e) {
            throw new ServiceException("Error within ReceiptServiceImpl delete(): " + e.getMessage(), e);
        } finally {
            receiptDao.getConnectionManager().releaseConnection();
        }
    }

    @Override
    public List<Receipt> findAll() throws ServiceException {
        try {
            List<Receipt> receiptList = receiptDao.findAll();
            Log.info("Receipts " + receiptList + " have been received from DB");
            return receiptList;
        } catch (PersistException e) {
            throw new ServiceException("Error within ReceiptServiceImpl findAll(): " + e.getMessage(), e);
        } finally {
            receiptDao.getConnectionManager().releaseConnection();
        }
    }

    @Override
    public List<Receipt> findAllByUserId(Long id) throws ServiceException {
        try {
            List<Receipt> receiptList = receiptDao.findAllByUserId(id);
            Log.info("Receipts " + receiptList + " have been received from DB by User with id - " + id);
            return receiptList;
        } catch (PersistException e) {
            throw new ServiceException("Error within ReceiptServiceImpl findAllByUserId(): " + e.getMessage(), e);
        } finally {
            receiptDao.getConnectionManager().releaseConnection();
        }
    }

    @Override
    public void transferBook(Long receiptId, Long statusId, Long bookId, Integer orderedBookQuantity, Integer availableBookQuantity) throws ServiceException, BusinessException {
        try {
            receiptDao.getConnectionManager().startTransaction();
            int updatedAmount = calculateTransferAmount(orderedBookQuantity, availableBookQuantity, statusId);
            Log.debug("Amount before update = " + availableBookQuantity + ". Ordered amount = " + orderedBookQuantity + ". Updated amount = " + updatedAmount + ".");
            if (updatedAmount < 0) {
                bookDao.getConnectionManager().rollbackTransaction();
                throw new BusinessException("Try to order more books than available");
            }
            Book book = new Book();
            book.setId(bookId);
            book.setAmount(updatedAmount);
            bookDao.updateAmountById(book);

            Receipt receipt = receiptDao.findOne(receiptId);
            Status status = new Status();
            status.setId(statusId);
            receipt.setStatus(status);

            receiptDao.updateStatusById(receipt);
            receiptDao.getConnectionManager().commitTransaction();
            receiptDao.getConnectionManager().enableAutoCommit();
        } catch (PersistException e) {
            receiptDao.getConnectionManager().rollbackTransaction();
            throw new ServiceException("Error within ReceiptServiceImpl transferBook(): " + e.getMessage(), e);
        } finally {
            receiptDao.getConnectionManager().releaseConnection();
        }
    }

    private int calculateTransferAmount(int orderedBookQuantity, int availableBookQuantity, Long statusId) throws BusinessException {
        int updatedAmount = 0;
        if (statusId.equals(Status.STATUS_REJECTED_ID)) {
            updatedAmount = availableBookQuantity;
        } else if (statusId.equals(Status.STATUS_RETURNED_ID)) {
            updatedAmount = availableBookQuantity + orderedBookQuantity;
        } else if (statusId.equals(Status.STATUS_TRANSFERRED_ID)) {
            updatedAmount = availableBookQuantity - orderedBookQuantity;
        }

        return updatedAmount;
    }

}
