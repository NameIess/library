package com.epam.training.library.daolayer.util;

import org.junit.Assert;
import org.junit.Test;
import resources.ResourceData;

import java.io.IOException;
import java.util.Properties;

public class DataReaderTest {
    private DataReader underTest = new DataReader();

    @Test
    public void shouldReturnNullWhenFilePathIsNull() throws IOException {
        Properties expected = underTest.parse(ResourceData.NULL_OBJECT);
        Assert.assertNull(expected);
    }
}
