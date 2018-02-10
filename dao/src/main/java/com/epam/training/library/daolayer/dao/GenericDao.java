package com.epam.training.library.daolayer.dao;

import com.epam.training.library.daolayer.dao.exception.PersistException;
import com.epam.training.library.daolayer.model.Identified;

import java.util.List;

/**
 * Contains the basic operations for interacting with models and connections for each entity
 *
 * @param <T> the type of the Identified object
 */
public interface GenericDao<T extends Identified> {

    /**
     * Saves entity to persistence data storage
     *
     * @param entity — an entity that must be saved
     * @throws PersistException when access error occurs
     */
    void create(T entity) throws PersistException;


    /**
     * Returns a particular entity with the specified id
     *
     * @param id — an entity identifier that is used to find a required instance
     * @return an instance of the entity with the given id
     *
     * @throws PersistException when access error occurs
     */
    T findOne(Long id) throws PersistException;


    /**
     * Updates a particular entity
     *
     * @param entity — an entity that must be updated
     * @throws PersistException when access error occurs
     */
    void update(T entity) throws PersistException;


    /**
     * Deletes a particular entity
     *
     * @param id — an entity identifier that is used to delete a required instance
     * @throws PersistException when access error occurs
     */
    void delete(Long id) throws PersistException;


    /**
     * Returns a list of all entities
     * @return a list of all entities
     *
     * @throws PersistException when access error occurs
     */
    List<T> findAll() throws PersistException;
}
