package com.yuuhikaze.exam_02.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerSingleton {

    private static final Logger logger = Logger.getLogger(LoggerSingleton.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler(DirManagerSingleton.getDataDir().resolve("logs/info.log").toString(), true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.severe("Error occurred while setting up file handler: " + e.getMessage());
        }
    }

    private static void log(String message, Level level) {
        logger.log(level, message);
    }

    public static void logInfo(String message) {
        log(message, Level.INFO);
    }

    public static void logWarning(String message) {
        log(message, Level.WARNING);
    }

    public static void logSevere(String message) {
        log(message, Level.SEVERE);
    }
}
