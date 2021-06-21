package com.newera.web.locators;

public class DatePickerLocator {
	protected String JQUERY_FROM_ID, JQUERY_TO_ID, JQUERY_DATE_PICKER_MON_DD_XPATH, JQUERY_DATE_PICKER_YEAR_XPATH,
			JQUERY_DATE_PICKER_PREV_MON_XPATH, JQUERY_DATE_PICKER_NEXT_MON_XPATH;

	public DatePickerLocator() {
// TODO Auto-generated constructor stub
		JQUERY_FROM_ID = "from";
		JQUERY_TO_ID = "to";
		JQUERY_DATE_PICKER_MON_DD_XPATH = "//div[@id='ui-datepicker-div']//select[@class='ui-datepicker-month']";
		JQUERY_DATE_PICKER_YEAR_XPATH = "//div[@id='ui-datepicker-div']//span[@class='ui-datepicker-year']";
		JQUERY_DATE_PICKER_PREV_MON_XPATH = "//div[@id='ui-datepicker-div']//a[@title='Prev']";
		JQUERY_DATE_PICKER_NEXT_MON_XPATH = "//div[@id='ui-datepicker-div']//a[@title='Next']";
	}

	protected String JQUERY_DATE_PICKER_PREV_DAY_XPATH(int date) {
		return "//div[@id='ui-datepicker-div']//td[@data-handler='selectDay']/a[text()=" + date + "]";
	}
}
