package com.epam.training.library.daolayer.validator;

/**
 * Contains the basic operation for interacting with objects that need to be validated
 *
 * @param <T> the type of the validated object
 */
public interface Verifiable<T> {

    /**
     * Returns the logic result of passing the validation
     *
     * @param object — instance that need to be validated
     * @return the logic result of passing the validation
     */
    boolean validate(T object);
}
