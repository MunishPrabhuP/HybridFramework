package com.newera.web.locators;

public class ProgressBarLocator {
	protected String DOWNLOAD_BTN_ID, DOWNLOAD_PROGRESS_XPATH;

	public ProgressBarLocator() {
		// TODO Auto-generated constructor stub
		DOWNLOAD_BTN_ID = "cricle-btn";
		DOWNLOAD_PROGRESS_XPATH = "//div[@class='prog-circle']/div[@class='percenttext']";
	}

	protected String PROGRESS_BAR(int progressBarId) {
		switch (progressBarId) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			return "//div[@id='slider" + progressBarId + "']//input[@name='range']";
		default:
			return "";
		}
	}

	protected String PROGRESS_BAR_VALUE(int progressBarId) {
		switch (progressBarId) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			return "//div[@id='slider" + progressBarId + "']//output[starts-with(@id,'range')]";
		default:
			return "";
		}
	}
}
