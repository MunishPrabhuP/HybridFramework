package com.newera.web.locators;

public class HomeLocator {
	public HomeLocator() {
		// TODO Auto-generated constructor stub
	}

	protected String MENU_DROPDOWN_XPATH(String menu) {
		return "//a[contains(text(),'" + menu + "')][@data-toggle='dropdown']";
	}

	protected String MENU_DROPDOWN_OPTION_XPATH(String option) {
		return "//ul[@class='dropdown-menu']/li[a='" + option + "']";
	}

	protected String MARKETING_POP_UP_XPATH(boolean wantToLearn) {
		if (wantToLearn) {
			return "//div[@id='at-cv-lightbox-button-holder']/a[starts-with(text(),'Yes')]";
		} else {
			return "//div[@id='at-cv-lightbox-button-holder']/a[starts-with(text(),'No')]";
		}
	}
}
