package com.qa.lib;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomLogger {
	private static Logger logger = null;

	static {
		synchronized (CustomLogger.class) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-a");
			System.setProperty("LOGGER_TIMESTAMP", dateFormat.format(new Date()));
			PropertyConfigurator.configure(System.getProperty("user.dir") + File.separator + "log4j.properties");
			logger = Logger.getLogger("automationlog");
		}
	}

	public static void info(String msg) {
		logger.info(msg);
	}

	public static void error(String msg) {
		logger.error(msg);
	}

	public static void error(String msg, Throwable t) {
		logger.error(msg, t);
	}

	public static void fatal(String msg) {
		logger.fatal(msg);
	}
}
