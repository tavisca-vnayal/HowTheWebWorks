package com.tavisca.workshops.Assignment1.Solution;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogsWriter {
    private Logger logger;

    LogsWriter(String className) throws IOException {
        this.logger = Logger.getLogger(className);
        FileHandler fileHandler = new FileHandler("MyLogFile.log");
        this.logger.addHandler(fileHandler);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
    }

    public void writeLog(String logMessage) {
        this.logger.info(logMessage);
    }
}
