package com.epam.training.library.daolayer.service;

import com.epam.training.library.daolayer.model.User;
import com.epam.training.library.daolayer.service.exception.BusinessException;
import com.epam.training.library.daolayer.service.exception.ServiceException;

import java.util.List;

/**
 * Contains the basic operations interaction with the users.
 */
public interface UserService extends Service {

    /**
     * Deletes a particular user with the specified id
     *
     * @param userId — a user identifier that is used to find and delete a required instance
     * @throws ServiceException when data access occurs
     */
    void delete(Long userId) throws ServiceException;

    /**
     * Returns a particular user with the specified id
     *
     * @param id — a user identifier that is used to find and return a required instance
     * @return an user instance with the specified id
     *
     * @throws ServiceException when data access occurs
     */
    User findOneById(Long id) throws ServiceException;

    /**
     * Returns a list of all users, registered in the system
     *
     * @return a list of all users, registered in the system
     *
     * @throws ServiceException when data access occurs
     */
    List<User> findAll() throws ServiceException;

    /**
     * Returns a particular user who registered in the system
     *
     * @param user — a user instance with specified data
     * @return a user instance who registered in the system
     *
     * @throws ServiceException when data access occurs
     */
    User findRegisteredUser(User user) throws ServiceException;

    /**
     * Saves the specified user
     *
     * @param user — user that must be saved
     * @throws ServiceException  when data access occurs
     * @throws BusinessException when the user with the same e-mail address is already exists in the system
     */
    void registration(User user) throws ServiceException, BusinessException;

    /**
     * Updates an existing book using the specified instance, if they are differ
     *
     * @param updatableUser — an instance of the updated book
     * @throws ServiceException when data access occurs
     */
    void update(User updatableUser) throws ServiceException;
}
