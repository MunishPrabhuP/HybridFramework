package com.newera.web.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.support.How;

import com.newera.lib.CustomLogger;
import com.newera.lib.Driver;
import com.newera.web.locators.DatePickerLocator;

public class DatePickerPage extends DatePickerLocator {
	Driver driver;

	public DatePickerPage(Driver driver) {
		// TODO Auto-generated constructor stub
		super();
		this.driver = driver;
	}

	public void chooseDateInJQueryCalendar(Date dt) {
		DateFormat dateFormatter = new SimpleDateFormat("d-MMM-yyyy");

		try {
			String formattedDate = dateFormatter.format(dt);
			String[] datePartition = formattedDate.split("-");

			String month;
			int date, year, displayYear;
			date = Integer.parseInt(datePartition[0]);
			month = datePartition[1];
			year = Integer.parseInt(datePartition[2]);

			displayYear = Integer.parseInt(driver.getText(How.XPATH, JQUERY_DATE_PICKER_YEAR_XPATH));
			while (displayYear > year) {
				gotoPreviousMonth();
				displayYear = Integer.parseInt(driver.getText(How.XPATH, JQUERY_DATE_PICKER_YEAR_XPATH));
			}
			while (displayYear < year) {
				gotoNextMonth();
				displayYear = Integer.parseInt(driver.getText(How.XPATH, JQUERY_DATE_PICKER_YEAR_XPATH));
			}
			driver.select(How.XPATH, JQUERY_DATE_PICKER_MON_DD_XPATH, month);
			driver.click(How.XPATH, JQUERY_DATE_PICKER_PREV_DAY_XPATH(date));
		} catch (Exception e) {
			// TODO: handle exception
			CustomLogger.error(e.getMessage(), e);
		}
	}

	public void chooseDatePicker(String option) {
		switch (option.toUpperCase()) {
		case "FROM":
			driver.click(How.ID, JQUERY_FROM_ID);
			break;
		case "TO":
			driver.click(How.ID, JQUERY_TO_ID);
			break;
		default:
			break;
		}
	}

	public void gotoNextMonth() {
		driver.click(How.XPATH, JQUERY_DATE_PICKER_NEXT_MON_XPATH);
	}

	public void gotoPreviousMonth() {
		driver.click(How.XPATH, JQUERY_DATE_PICKER_PREV_MON_XPATH);
	}

	public boolean isNextMonthSelectorEnabled() {
		return driver.isEnabled(How.XPATH, JQUERY_DATE_PICKER_NEXT_MON_XPATH);
	}

	public boolean isPreviousMonthSelectorEnabled() {
		return driver.isEnabled(How.XPATH, JQUERY_DATE_PICKER_PREV_MON_XPATH);
	}
}
