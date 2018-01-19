package dao;

import dao.exception.PersistException;
import model.Identified;

import java.util.List;

public interface GenericDao<T extends Identified> {

    void create(T entity) throws PersistException;

    T findOne(Long id) throws PersistException;

    void update(T entity) throws PersistException;

    void delete(T entity) throws PersistException;

    List<T> findAll() throws PersistException;

    void startTransaction();

    void stopTransaction();

    void commit();

    void rollback();

    void releaseConnection();
}
