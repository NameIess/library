package com.epam.training.library.daolayer.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DataReader {
    private static final Logger Log = LogManager.getLogger(DataReader.class.getSimpleName());
    private Properties props;

    public DataReader() {
        this.props = new Properties();
    }

    public Properties parse(String filePath) throws IOException {
        if (filePath == null) {
            Log.error("Null pointer path");
            return null;
        }

        ClassLoader classLoader = this.getClass().getClassLoader();
        URL classLoaderResource = classLoader.getResource(filePath);
        String path = classLoaderResource.getPath();
        props.load(new FileReader(path));

        Log.info("Data has been read from " + filePath);

        return props;
    }
}
