package service.impl;

import dao.BookDao;
import dao.ReceiptDao;
import dao.exception.PersistException;
import model.Book;
import model.Receipt;
import model.Status;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ReceiptService;
import service.exception.ServiceException;

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
            receiptDao.startTransaction();
            receiptDao.create(receipt);
            receiptDao.commit();
        } catch (PersistException e) {
            receiptDao.rollback();
            throw new ServiceException("Error within ReceiptServiceImpl save(): " + e.getMessage(), e);
        } finally {
            receiptDao.releaseConnection();
        }
    }

    @Override
    public void delete(Receipt receipt) throws ServiceException {
        try {
            receiptDao.delete(receipt);
        } catch (PersistException e) {
            throw new ServiceException("Error within ReceiptServiceImpl delete(): " + e.getMessage(), e);
        } finally {
            receiptDao.releaseConnection();
        }
    }

    @Override
    public Receipt findOneById(Long id) throws ServiceException {
        Receipt receipt;
        try {
            receipt = receiptDao.findOne(id);
        } catch (PersistException e) {
            throw new ServiceException("Error within ReceiptServiceImpl findOneById(): " + e.getMessage(), e);
        } finally {
            receiptDao.releaseConnection();
        }
        return receipt;
    }

    @Override
    public List<Receipt> findAll() throws ServiceException {
        List<Receipt> receiptList;
        try {
            receiptList = receiptDao.findAll();
        } catch (PersistException e) {
            throw new ServiceException("Error within ReceiptServiceImpl findAll(): " + e.getMessage(), e);
        } finally {
            receiptDao.releaseConnection();
        }
        return receiptList;
    }

    @Override
    public List<Receipt> findAllByUserId(User user) throws ServiceException {
        List<Receipt> receiptList;
        try {
            Long id = user.getId();
            receiptList = receiptDao.findAllByUserId(id);
        } catch (PersistException e) {
            throw new ServiceException("Error within ReceiptServiceImpl findAllByUserId(): " + e.getMessage(), e);
        } finally {
            receiptDao.releaseConnection();
        }

        return receiptList;
    }

    @Override
    public void transferBook(Long receiptId, Long statusId, Long bookId, Integer orderedBookQuantity, Integer availableBookQuantity) throws ServiceException {
        try {
            receiptDao.startTransaction();
            bookDao.startTransaction();
            Receipt receipt = receiptDao.findOne(receiptId);
            Status status = new Status();
            status.setId(statusId);
            receipt.setStatus(status);
            Log.debug("Receipt before status update : = " + receipt);
            receiptDao.updateStatusById(receipt);

            Book book = new Book();
            book.setId(bookId);
            Log.debug("Amount before update : =" + availableBookQuantity);
            Log.debug("Ordered amount : = " + orderedBookQuantity);
            Integer updatedAmount = calculateTransferAmount(orderedBookQuantity, availableBookQuantity, statusId);
            Log.debug("Updated amount : = " + updatedAmount);
            book.setAmount(updatedAmount);
            bookDao.updateAmountById(book);
            receiptDao.commit();
            bookDao.commit();
        } catch (PersistException e) {
            receiptDao.rollback();
            bookDao.rollback();
            throw new ServiceException("Error within ReceiptServiceImpl transferBook(): " + e.getMessage(), e);
        } finally {
            receiptDao.releaseConnection();
            bookDao.releaseConnection();
        }
    }

    private int calculateTransferAmount(int orderedBookQuantity, int availableBookQuantity, Long statusId) {
        int updatedAmount = 0;
        if (statusId == Status.STATUS_TRANSFERRED_ID) {
            updatedAmount = availableBookQuantity - orderedBookQuantity;
        } else if (statusId == Status.STATUS_REJECTED_ID) {
            updatedAmount = availableBookQuantity;
        } else if (statusId == Status.STATUS_RETURNED_ID) {
            updatedAmount = availableBookQuantity + orderedBookQuantity;
        }

        return updatedAmount;
    }

}
