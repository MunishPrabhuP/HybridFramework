package com.newera.web.pages;

import org.openqa.selenium.support.How;

import com.newera.lib.CustomLogger;
import com.newera.lib.Driver;
import com.newera.web.locators.ProgressBarLocator;

public class ProgressBarPage extends ProgressBarLocator {
	Driver driver;

	public ProgressBarPage(Driver driver) {
		// TODO Auto-generated constructor stub
		super();
		this.driver = driver;
	}

	public void clickDownloadBtn() {
		CustomLogger.info("Start Downloading");
		while (getDownloadProgress().contains("0")) {
			driver.click(How.ID, DOWNLOAD_BTN_ID);
		}
	}

	public void displayDownloadProgress() {
		CustomLogger.info("Download In Progress");

		String downloadProgress = getDownloadProgress();
		while (!(downloadProgress.contains("100"))) {
			CustomLogger.info(downloadProgress);
			downloadProgress = getDownloadProgress();
		}
	}

	public String getDownloadProgress() {
		return driver.getText(How.XPATH, DOWNLOAD_PROGRESS_XPATH);
	}

//	progressBarId -> 1 - 6
	public int getProgressBarWidth(int progressBarId) {
		int progresssBarWidth = 0;

		switch (progressBarId) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			progresssBarWidth = driver.getSize(How.XPATH, PROGRESS_BAR(progressBarId)).getWidth();
			break;
		default:
			break;
		}
		return progresssBarWidth;
	}

	public int adjustProgressBarHandle(int progressBarId, int width) {
		switch (progressBarId) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			driver.moveAndClick(How.XPATH, PROGRESS_BAR(progressBarId), width, 0);
			break;
		default:
			break;
		}
		return getProgressBarValue(progressBarId);
	}

	public int getProgressBarValue(int progressBarId) {
		int progressBarVal = 0;

		switch (progressBarId) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			progressBarVal = Integer.parseInt(driver.getText(How.XPATH, PROGRESS_BAR_VALUE(progressBarId)));
			break;
		default:
			break;
		}
		return progressBarVal;
	}
}
