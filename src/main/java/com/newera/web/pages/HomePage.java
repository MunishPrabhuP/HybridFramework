package com.newera.web.pages;

import org.openqa.selenium.support.How;

import com.newera.lib.Driver;
import com.newera.web.locators.HomeLocator;

public class HomePage extends HomeLocator{
	Driver driver;

	public HomePage(Driver driver) {
		// TODO Auto-generated constructor stub
		super();
		this.driver = driver;
		closeMarketingPopUp();
	}

	public void selectMenu(String menu) {
		driver.click(How.XPATH, MENU_DROPDOWN_XPATH(menu));
	}

	public void selectMenuItem(String menu, String item) {
		selectMenu(menu);
		driver.click(How.XPATH, MENU_DROPDOWN_OPTION_XPATH(item));
	}

	public void closeMarketingPopUp() {
		driver.click(How.XPATH, MARKETING_POP_UP_XPATH(false));
	}
}
