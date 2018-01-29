package com.epam.training.library.daolayer.validator;

public interface Verifiable<T> {

    boolean validate(T object);
}
