package com.epam.training.library.daolayer.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncoder implements Encryptable {


    @Override
    public String encryptMd5WithPostfixSalt(String encryptable, String salt) {
        String encodableGroup = encryptable.concat(salt);
        String md5HexString = DigestUtils.md5Hex(encodableGroup);
        return md5HexString;
    }
}
