package com.newera.web.locators;

public class AlertsWindowModelLocator {
	protected String ALERT_BTN_XPATH, FILE_DOWNLOAD_TEXTAREA_ID, GENERATE_FILE_BTN_ID, FILE_DOWNLOAD_LINK_ID;

	public AlertsWindowModelLocator() {
		// TODO Auto-generated constructor stub
		ALERT_BTN_XPATH = "//button[text()='Click me!'][contains(@onclick,'myAlertFunction')]";
		FILE_DOWNLOAD_TEXTAREA_ID = "textbox";
		GENERATE_FILE_BTN_ID = "create";
		FILE_DOWNLOAD_LINK_ID = "link-to-download";
	}

	protected String WINDOW_MODEL_BTN_XPATH(String buttonText) {
		return "//a[text()='" + buttonText + "']";
	}
}
