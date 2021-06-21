package com.newera.web.testcases;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.github.javafaker.Faker;
import com.newera.lib.CustomLogger;
import com.newera.lib.Driver;
import com.newera.utils.Utilities;
import com.newera.web.lib.WebHelper;
import com.newera.web.pages.AjaxFormPage;
import com.newera.web.pages.AlertsWindowModelPage;
import com.newera.web.pages.DatePickerPage;
import com.newera.web.pages.HomePage;
import com.newera.web.pages.ProgressBarPage;
import com.newera.web.utils.Constants;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class WebAutomationCases {
	private Driver driver;
	private HomePage homePage;
	private AjaxFormPage ajaxFormPage;
	private ProgressBarPage progressBarPage;
	private AlertsWindowModelPage alertWindowModelPage;
	private DatePickerPage datePickerPage;
	private Faker faker;

	@BeforeClass
	public void setUp(ITestContext context) {
		CustomLogger.info("TestCase execution started");
		driver = new Driver(WebHelper.ENV_PROP.getProperty("BROWSER"),
				WebHelper.ENV_PROP.getProperty("BASE_URL") + WebHelper.ENV_PROP.getProperty("BASE_PATH"));
		context.setAttribute("Driver", driver);
		homePage = new HomePage(driver);
		ajaxFormPage = new AjaxFormPage(driver);
		progressBarPage = new ProgressBarPage(driver);
		alertWindowModelPage = new AlertsWindowModelPage(driver);
		datePickerPage = new DatePickerPage(driver);
		faker = new Faker();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, description = "Verify Ajax Form Submit", enabled = true)
	@Feature("UI Automation")
	@Story("Selenium Easy")
	@Severity(SeverityLevel.NORMAL)
	public void verifyInputForm() {
		String name = faker.funnyName().name(), comment = faker.lorem().paragraph();

		homePage.selectMenuItem("Input Forms", "Ajax Form Submit");
		ajaxFormPage.inputFormDetails(name, comment);
		Assert.assertTrue(ajaxFormPage.waitForFormSubmissionMessage(Constants.AJAX_FORM_SUCCESSFUL_MSG),
				"Ajax Form Submission failed");
	}

	@Test(priority = 2, description = "Verify Download Progress and Log the Percentage", enabled = true)
	@Feature("UI Automation")
	@Story("Selenium Easy")
	@Severity(SeverityLevel.NORMAL)
	public void verifyProgressBar() {
		homePage.selectMenuItem("Progress Bars", "Bootstrap Progress bar");
		progressBarPage.clickDownloadBtn();
		Instant start = Instant.now();
		progressBarPage.displayDownloadProgress();
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		Assert.assertEquals(progressBarPage.getDownloadProgress(), "100%");
		CustomLogger.info("Total Download Duration:(In Seconds)" + timeElapsed.getSeconds());
	}

	@Test(priority = 3, description = "Verify Slider", enabled = true)
	@Feature("UI Automation")
	@Story("Selenium Easy")
	@Severity(SeverityLevel.NORMAL)
	public void verifySlider() {
		SoftAssert s_assert = new SoftAssert();
		int progressBarPercentage = 50, progressBarWidth;

		homePage.selectMenuItem("Progress Bars", "Drag & Drop Sliders");
		for (int i = 1; i < 6; i++) {
			progressBarWidth = progressBarPage.getProgressBarWidth(i);
			s_assert.assertEquals(progressBarPage.adjustProgressBarHandle(i, (progressBarWidth / 2)),
					progressBarPercentage);
		}
		s_assert.assertAll();
	}

	@Test(priority = 4, description = "Verify Multi Window Model Titles", enabled = true)
	@Feature("UI Automation")
	@Story("Selenium Easy")
	@Severity(SeverityLevel.NORMAL)
	public void verifyMultiWindowModel() {
		List<String> windowHndls;

		homePage.selectMenuItem("Alerts & Modals", "Window Popup Modal");
		windowHndls = alertWindowModelPage.openModel("Follow Twitter & Facebook");
		Assert.assertEquals(windowHndls.size(), 3);
		CustomLogger.info("Switching to first Window");
		driver.switchToWindow(windowHndls.get(1));
		CustomLogger.info("First Window Title -> " + driver.getTitle());
		CustomLogger.info("Switching to second Window");
		driver.switchToWindow(windowHndls.get(2));
		CustomLogger.info("Second Window Title -> " + driver.getTitle());
		CustomLogger.info("Switching to parent Window");
		driver.switchToWindow(windowHndls.get(0));
		CustomLogger.info("Parent Window Title -> " + driver.getTitle());
	}

	@Test(priority = 5, description = "Verify Javascript Alert Box", enabled = true)
	@Feature("UI Automation")
	@Story("Selenium Easy")
	@Severity(SeverityLevel.NORMAL)
	public void verifyJavascriptAlerts() {
		homePage.selectMenuItem("Alerts & Modals", "Javascript Alerts");
		alertWindowModelPage.openJSAlert();
		Assert.assertTrue(driver.isAlertPresent(), "Alert not present");
		driver.dismissAlert();
	}

	@Test(priority = 6, description = "Verify File Download", enabled = true)
	@Feature("UI Automation")
	@Story("Selenium Easy")
	@Severity(SeverityLevel.NORMAL)
	public void verifyFileDownload() throws Exception {
		String comment = faker.lorem().paragraph(), fileContent = "";
		File file = new File(WebHelper.FILE_DOWNLOAD_PATH + File.separator + "easyinfo.txt");
		try {
			FileUtils.delete(file);
		} catch (Exception e) {
			// TODO: handle exception
			CustomLogger.error(e.getMessage(), e);
		}

		homePage.selectMenuItem("Alerts & Modals", "File Download");
		alertWindowModelPage.downloadFileWithGivenContent(comment);
		try {
			fileContent = Utilities.readTextFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			CustomLogger.error(e.getMessage(), e);
		}
		Assert.assertEquals(fileContent, comment);
	}

	@Test(priority = 7, description = "Verify Date Picker", enabled = true)
	@Feature("UI Automation")
	@Story("Selenium Easy")
	@Severity(SeverityLevel.NORMAL)
	public void verifyDatePicker() throws Exception {
		DateFormat dateFormatter = new SimpleDateFormat("MMMM d, yyyy");
		String from = "October 1, 2020", to = "November 1, 2020";
		Date fromDate = dateFormatter.parse(from), toDate = dateFormatter.parse(to);
		SoftAssert s_assert = new SoftAssert();

		homePage.selectMenuItem("Date pickers", "JQuery Date Picker");
		datePickerPage.chooseDatePicker("From");
		datePickerPage.chooseDateInJQueryCalendar(fromDate);
		datePickerPage.chooseDatePicker("To");
		datePickerPage.chooseDateInJQueryCalendar(toDate);
		datePickerPage.chooseDatePicker("From");
		datePickerPage.gotoNextMonth();
		s_assert.assertTrue(datePickerPage.isNextMonthSelectorEnabled(), "Able to choose beyond " + to);
		datePickerPage.chooseDatePicker("To");
		datePickerPage.gotoPreviousMonth();
		s_assert.assertTrue(datePickerPage.isPreviousMonthSelectorEnabled(), "Able to choose behind " + to);
		s_assert.assertAll();
	}
}
