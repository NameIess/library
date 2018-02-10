package com.epam.training.library.daolayer.util;

/**
 * Contains the basic operation for interacting with strings that need to be encrypted
 */
public interface Encryptable {

    /**
     * Encrypts string using additional 'salt' parameter
     *
     * @param encryptable — the main string that need to be encrypted
     * @param salt        — the additional string that joins the end of the main line
     * @return both lines are connected and encrypted using the MD5 encryption method
     */
    String encryptMd5WithPostfixSalt(String encryptable, String salt);
}
