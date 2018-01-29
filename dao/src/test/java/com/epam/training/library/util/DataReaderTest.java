package com.epam.training.library.util;

import com.epam.training.library.daolayer.util.DataReader;
import org.junit.Assert;
import org.junit.Test;
import resources.ResourceData;

import java.util.Properties;

public class DataReaderTest {
    private DataReader underTest = new DataReader();

    @Test
    public void shouldReturnNullWhenFilePathIsNull() {
        Properties expected = underTest.parse(ResourceData.NULL_OBJECT);
        Assert.assertNull(expected);
    }
}
