package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ResourceBundle;

public class MessageManager {
    private static final Logger Log = LogManager.getLogger(MessageManager.class.getSimpleName());
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("local");

    private MessageManager() {}

    public static String getProperty(String key) {
        Log.debug("Before getString()");
        String message = resourceBundle.getString(key);
        Log.debug("After getString(): " + message);
        return message;
    }
}
