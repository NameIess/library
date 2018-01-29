package com.epam.training.library.daolayer.util;

public interface Encryptable {

    String encryptMd5WithPostfixSalt(String encryptable, String salt);
}
