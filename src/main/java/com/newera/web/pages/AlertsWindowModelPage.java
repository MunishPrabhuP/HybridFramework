package com.newera.web.pages;

import java.util.List;

import org.openqa.selenium.support.How;

import com.newera.lib.Driver;
import com.newera.web.locators.AlertsWindowModelLocator;

public class AlertsWindowModelPage extends AlertsWindowModelLocator {
	Driver driver;

	public AlertsWindowModelPage(Driver driver) {
		// TODO Auto-generated constructor stub
		super();
		this.driver = driver;
	}

	public List<String> openModel(String modelButtonText) {
		driver.click(How.XPATH, WINDOW_MODEL_BTN_XPATH(modelButtonText));
		return driver.getWindowHandles();
	}

	public void openJSAlert() {
		driver.click(How.XPATH, ALERT_BTN_XPATH);
	}

	public void downloadFileWithGivenContent(String content) throws InterruptedException {
		generateFile(content);
		driver.click(How.ID, FILE_DOWNLOAD_LINK_ID);
		Thread.sleep(3000);
	}

	public void generateFile(String content) {
		driver.sendKeys(How.ID, FILE_DOWNLOAD_TEXTAREA_ID, content);
		driver.click(How.ID, GENERATE_FILE_BTN_ID);
	}
}
