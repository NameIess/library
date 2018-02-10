package com.epam.training.library.daolayer.util;

import com.epam.training.library.daolayer.util.PasswordEncoder;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import resources.ResourceData;

@RunWith(DataProviderRunner.class)
public class PasswordEncoderTest {
    private PasswordEncoder underTest = new PasswordEncoder();


    @DataProvider
    public static Object[][] validMd5EncodedString() {
        return new Object[][]{
                {ResourceData.validMd5Encoding.get(0), ResourceData.validMd5Encoding.get(1), ResourceData.validMd5Encoding.get(2)},
                {ResourceData.validMd5Encoding.get(3), ResourceData.validMd5Encoding.get(4), ResourceData.validMd5Encoding.get(5)},
                {ResourceData.validMd5Encoding.get(6), ResourceData.validMd5Encoding.get(7), ResourceData.validMd5Encoding.get(8)}
        };
    }

    @Test
    @UseDataProvider("validMd5EncodedString")
    public void shouldReturnTrueWhenEncodingCorrect(String encrypted, String salt, String expectedResult) {
        String currentResult = underTest.encryptMd5WithPostfixSalt(encrypted, salt);
        Assert.assertEquals(currentResult, expectedResult);
    }
}
