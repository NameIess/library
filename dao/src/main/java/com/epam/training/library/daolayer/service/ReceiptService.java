package com.epam.training.library.daolayer.service;

import com.epam.training.library.daolayer.model.Receipt;
import com.epam.training.library.daolayer.model.User;
import com.epam.training.library.daolayer.service.exception.BusinessException;
import com.epam.training.library.daolayer.service.exception.ServiceException;

import java.util.List;

public interface ReceiptService {
    void save(Receipt receipt) throws ServiceException;

    void delete(Receipt receipt) throws ServiceException;

    Receipt findOneById(Long id) throws ServiceException;

    List<Receipt> findAll() throws ServiceException;

    List<Receipt> findAllByUserId(User user) throws ServiceException;

    void transferBook(Long receiptId, Long statusId, Long bookId, Integer orderedBookQuantity, Integer availableBookQuantity) throws ServiceException, BusinessException;
}
