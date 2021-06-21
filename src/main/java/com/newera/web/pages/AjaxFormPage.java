package com.newera.web.pages;

import org.openqa.selenium.support.How;

import com.newera.lib.Driver;
import com.newera.web.locators.AjaxFormLocator;

public class AjaxFormPage extends AjaxFormLocator {
	Driver driver;

	public AjaxFormPage(Driver driver) {
		super();
		this.driver = driver;
	}

	public void inputFormDetails(String name, String comment) {
		driver.sendKeys(How.ID, TITLE_ID, name);
		driver.sendKeys(How.ID, DESCRIPTION_ID, comment);
		driver.click(How.ID, SUBMIT_BTN_ID);
	}

	public boolean waitForFormSubmissionMessage(String message) {
		return driver.isTextPresent(How.ID, SUCCESS_MSG_ID, message);
	}
}
