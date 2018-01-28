package util;

public interface Encryptable {

    String encryptMd5WithPostfixSalt(String encryptable, String salt);
}
