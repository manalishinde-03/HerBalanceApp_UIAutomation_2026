package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggerFactory {
    private static final Logger logger = LogManager.getLogger(LoggerFactory.class);

    public static Logger getLogger() {
        return logger;
    }

    public static void info(String message, Object... args) {
        logger.info(message, args);
    }

    public static void warn(String message, Object... args) {
        logger.warn(message, args);
    }

    public static void error(String message, Object... args) {
        logger.error(message, args);
    }

    public static void debug(String message, Object... args) {
        logger.debug(message, args);
    }
}
