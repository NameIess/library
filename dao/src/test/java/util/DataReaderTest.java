package util;

import org.junit.Assert;
import org.junit.Test;
import resources.ResourceData;

import java.util.Properties;

public class DataReaderTest {
    private DataReader underTest = new DataReader();

    @Test
    public void shouldThrowExceptionWhenFilePathIsNull() {
        Properties expected = underTest.parse(ResourceData.NULL_OBJECT);
        Assert.assertNull(expected);
    }
}
