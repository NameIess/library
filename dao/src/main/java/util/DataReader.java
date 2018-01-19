package util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataReader {
    private static final Logger log = LogManager.getLogger(DataReader.class.getSimpleName());
    private Properties props;


    public DataReader() {
        this.props = new Properties();
    }

    public Properties parse(String filePath) {
        if (filePath == null) {
            log.log(Level.ERROR, "Null pointer path");
            return null;
        }

        try {
            String path = this.getClass().getClassLoader().getResource(filePath).getPath();
            props.load(new FileReader(path));

            log.info("Data has been read from " + filePath);
        } catch (IOException e) {
            log.log(Level.FATAL, "ReaderException: " + e.getMessage(), e);
        }

        return props;
    }
}
