package com.newera.api.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import com.newera.lib.CustomLogger;

public class ApiHelper {
	private static String API_HOME_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "java" + File.separator + "com" + File.separator + "newera" + File.separator
			+ "api";
	private static String ALLURE_REPORT_HOME_DIR = System.getProperty("user.dir") + File.separator + "allure-results";
	public static Properties END_POINT_PROP = new Properties();
	public static Properties ENV_PROP = new Properties();

	public static void loadEndPoints() throws IOException {
		FileInputStream fis = new FileInputStream(
				API_HOME_PATH + File.separator + "config" + File.separator + "end_points.properties");
		END_POINT_PROP = new Properties();
		END_POINT_PROP.load(fis);
	}

	public static void loadEnviroment() throws IOException {
		FileInputStream fis = new FileInputStream(API_HOME_PATH + File.separator + "environment_prop" + File.separator
				+ "qa" + File.separator + "accounts.properties");
		ENV_PROP = new Properties();
		ENV_PROP.load(fis);
	}

	public static void setUpEnvironment() {
		try {
			FileUtils.deleteDirectory(new File(ALLURE_REPORT_HOME_DIR));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			CustomLogger.error(e.getMessage(), e);
		}
	}
}
