package framework.utils;

import org.testng.Reporter;

public final class Logger {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);
    private static Logger instance = null;
    private static boolean logSteps = true;

    public Logger() {
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void step(final int step) {
        logDelimMsg(String.valueOf(step));
    }

    private void logDelimMsg(final String msg) {
        if (logSteps) {
            info(String.format("--------==[ %1$s ]==--------", msg));
        }
    }

    public void info(final String message) {
        logger.info(message);
        Reporter.log(message + "<br>");
    }


    public void warn(final String message) {
        String msg = message;
        logger.warn(message);
    }

    public void error(final String message) {
        String msg = message;
        logger.error(message);
        Reporter.log(msg + "<br>");
    }


    public void fatal(final String message) {
        String msg = message;
        logger.fatal(message);
        Reporter.log(msg + "<br>");
    }
}
