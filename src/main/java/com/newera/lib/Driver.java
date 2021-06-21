package com.newera.lib;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newera.web.lib.WebHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
	final private int TIME_OUT = 15;
	private WebDriver driver;
	private WebDriverWait wait;

	public Driver(String browser, String uri) {
		// TODO Auto-generated constructor stub
		initDriver(browser);
		driver.get(uri);
	}

	private void initDriver(String browser) {
		DesiredCapabilities capabilities = null;
		String downloadFilepath = WebHelper.FILE_DOWNLOAD_PATH;

		if (browser.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.download.dir", downloadFilepath);
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"text/csv,application/java-archive, application/x-msexcel,application/excel,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/vnd.microsoft.portable-executable,application/octet-stream");
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("pdfjs.disabled", true);
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			Map<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("disable-infobars");
			chromeOptions.addArguments("start-fullscreen");
			chromeOptions.setExperimentalOption("prefs", chromePrefs);
			capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			chromeOptions.merge(capabilities);
			driver = new ChromeDriver(chromeOptions);
		} else if (browser.equalsIgnoreCase("Edge") || browser.equalsIgnoreCase("Microsoft Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("IE") || browser.equalsIgnoreCase("Internet Explorer")) {
			WebDriverManager.iedriver().setup();
			driver = new EdgeDriver();
		}
		wait = new WebDriverWait(driver, TIME_OUT);
	}

	public void get(String url) {
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TIME_OUT, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(TIME_OUT, TimeUnit.SECONDS);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public WebElement findElement(How how, String by) {
		WebElement webElement = null;

		try {
			if (how.equals(How.ID)) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(by)));
				webElement = driver.findElement(By.id(by));
			} else if (how.equals(How.NAME)) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(by)));
				webElement = driver.findElement(By.name(by));
			} else if (how.equals(How.XPATH)) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(by)));
				webElement = driver.findElement(By.xpath(by));
			} else if (how.equals(How.CSS)) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(by)));
				webElement = driver.findElement(By.cssSelector(by));
			} else if (how.equals(How.CLASS_NAME)) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(by)));
				webElement = driver.findElement(By.className(by));
			} else if (how.equals(How.TAG_NAME)) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(by)));
				webElement = driver.findElement(By.tagName(by));
			} else if (how.equals(How.LINK_TEXT)) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(by)));
				webElement = driver.findElement(By.linkText(by));
			} else if (how.equals(How.PARTIAL_LINK_TEXT)) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(by)));
				webElement = driver.findElement(By.partialLinkText(by));
			}
		} catch (Exception e) {
			// TODO: handle exception
			CustomLogger.error(e.getMessage(), e);
		}
		return webElement;
	}

	public List<WebElement> findElements(How how, String by) {
		List<WebElement> webElements = null;

		try {
			if (how.equals(How.ID)) {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(by)));
				webElements = driver.findElements(By.id(by));
			} else if (how.equals(How.NAME)) {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name(by)));
				webElements = driver.findElements(By.name(by));
			} else if (how.equals(How.XPATH)) {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(by)));
				webElements = driver.findElements(By.xpath(by));
			} else if (how.equals(How.CSS)) {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(by)));
				webElements = driver.findElements(By.cssSelector(by));
			} else if (how.equals(How.CLASS_NAME)) {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(by)));
				webElements = driver.findElements(By.className(by));
			} else if (how.equals(How.TAG_NAME)) {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(by)));
				webElements = driver.findElements(By.tagName(by));
			} else if (how.equals(How.LINK_TEXT)) {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText(by)));
				webElements = driver.findElements(By.linkText(by));
			} else if (how.equals(How.PARTIAL_LINK_TEXT)) {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.partialLinkText(by)));
				webElements = driver.findElements(By.partialLinkText(by));
			}
		} catch (Exception e) {
			// TODO: handle exception
			CustomLogger.error(e.getMessage(), e);
		}
		return webElements;
	}

	public void click(How how, String by) {
		WebElement element;

		element = findElement(how, by);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void sendKeys(How how, String by, String data) {
		WebElement element;

		element = findElement(how, by);
		element.sendKeys(data);
	}

	public void moveAndClick(How how, String by, int x, int y) {
		Actions action = new Actions(driver);
		WebElement element;

		element = findElement(how, by);
		action.moveToElement(element, x, y).click(element).build().perform();
	}

	public void select(How how, String by, String input) {
		WebElement element;
		Select dropDown;
		List<WebElement> slctElements, options;
		String multiInput[] = null;
		boolean multiInputFlag = false;
		int attempts = 0;

		if (input.contains(",")) {
			multiInput = input.split(",");
			multiInputFlag = true;
		}
		while (attempts < 3) {
			try {
				element = findElement(how, by);
				dropDown = new Select(element);
				options = dropDown.getOptions();
				if (dropDown.isMultiple()) {
					slctElements = dropDown.getAllSelectedOptions();
					if (slctElements.size() > 0)
						dropDown.deselectAll();
				}
				if (multiInputFlag) {
					for (int multiIndex = 0; multiIndex < multiInput.length; multiIndex++) {
						for (int index = 0; index < options.size(); index++) {
							if (options.get(index).getText().trim().equalsIgnoreCase(multiInput[multiIndex].trim())) {
								dropDown.selectByIndex(index);
								break;
							}
						}
					}
				} else {
					for (int index = 0; index < options.size(); index++) {
						if (options.get(index).getText().trim().equalsIgnoreCase(input)) {
							dropDown.selectByIndex(index);
							break;
						} else if (options.get(index).getText().trim().toUpperCase().contains(input.toUpperCase()))
							dropDown.selectByIndex(index);
					}
				}
				break;
			} catch (StaleElementReferenceException e) {
				CustomLogger.error(e.getMessage(), e);
				element = null;
			}
			attempts++;
		}
	}

	public void checkbox(How how, String by, String act) {
		WebElement element;

		element = findElement(how, by);
		if (element.isDisplayed()) {
			if (element.isEnabled()) {
				if (act.equalsIgnoreCase("Check")) {
					if (!element.isSelected())
						click(how, by);
				} else if (act.equalsIgnoreCase("UnCheck")) {
					if (element.isSelected())
						click(how, by);
				}
			}
		}
	}

	public String getText(How how, String by) {
		String text = null;
		WebElement element;

		element = findElement(how, by);
		text = element.getText().trim();
		return text;
	}

	public void acceptAlert() {
		Alert wdAlert;

		wdAlert = driver.switchTo().alert();
		wdAlert.accept();
	}

	public void dismissAlert() {
		Alert wdAlert;

		wdAlert = driver.switchTo().alert();
		wdAlert.dismiss();
	}

	public boolean isAlertPresent() {
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			CustomLogger.error(e.getMessage(), e);
			return false;
		}
	}

	public void switchToWindow(String handleId) {
		driver.switchTo().window(handleId);
	}

	public void switchToFrame(How how, String by) {
		WebElement element;

		element = findElement(how, by);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public List<String> getWindowHandles() {
		List<String> windows = new ArrayList<String>(driver.getWindowHandles());
		return windows;
	}

	public boolean isTextPresent(How how, String by, String text) {
		WebElement element;

		element = findElement(how, by);
		try {
			wait.until(ExpectedConditions.textToBePresentInElement(element, text));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			CustomLogger.error(e.getMessage(), e);
			return false;
		}
	}

	public boolean isElementPresent(How how, String by) {
		List<WebElement> elements;

		elements = findElements(how, by);
		if (elements.size() > 0)
			return true;
		else
			return false;
	}

	public Dimension getSize(How how, String by) {
		WebElement element;

		element = findElement(how, by);
		return element.getSize();
	}

	public boolean isEnabled(How how, String by) {
		WebElement element;

		element = findElement(how, by);
		return element.isEnabled();
	}

	public byte[] takeScreenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	public void takeScreenshot(String screenShotPath) throws IOException {
		File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File fl = new File(screenShotPath);
		FileUtils.copyFile(screenShotFile, fl);
	}

	public void close() {
		driver.close();
	}

	public void quit() {
		driver.quit();
	}
}
