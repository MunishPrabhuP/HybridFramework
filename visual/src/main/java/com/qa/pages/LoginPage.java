package com.qa.pages;

import com.qa.clients.SeleniumDriver;
import com.qa.locators.LoginLocator;
import org.openqa.selenium.support.How;


public class LoginPage extends LoginLocator {
    SeleniumDriver driver;

    public LoginPage(SeleniumDriver driver) {
        this.driver = driver;
    }

    public void login(String userEmail, String userPassword) {
        driver.sendKeys(How.NAME, USEREMAIL_NAME, userEmail);
        driver.click(How.ID, NEXT_BTN_ID);
        driver.sendKeys(How.NAME, PASSWORD_NAME, userPassword);
        driver.click(How.ID, SIGN_IN_BTN_ID);
    }
}
