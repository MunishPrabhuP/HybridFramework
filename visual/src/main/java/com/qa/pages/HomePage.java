package com.qa.pages;

import com.qa.clients.SeleniumDriver;
import com.qa.locators.HomeLocator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.How;

public class HomePage extends HomeLocator {
    SeleniumDriver driver;

    public HomePage(SeleniumDriver driver) {
        this.driver = driver;
    }

    public void navigateToRightMenu(String menu) {
        driver.click(How.XPATH, RIGHT_NAVIGATION_MENU_XPATH(menu));
    }

    public void navigateToExpandedRightSubMenu(String menu) {
        driver.click(How.XPATH, RIGHT_NAVIGATION_SUB_MENU_XPATH(menu));
    }

    public void searchTenant(String tenant) throws InterruptedException {
        Thread.sleep(5000);
        driver.refresh();
        waitForTenantsToLoadInGrid();
        driver.click(How.ID, CLOUD_LICENSES_SEARCH_TENANT_ID);
        Thread.sleep(1000);
        driver.sendKeys(How.ID, CLOUD_LICENSES_SEARCH_TENANT_ID, tenant);
        Thread.sleep(1000);
        driver.sendKeys(How.ID, CLOUD_LICENSES_SEARCH_TENANT_ID, Keys.ENTER);
        driver.isElementPresentWithWait(How.XPATH, TENANT_TIME_RANGE_SELECTOR_XPATH, 30);
        waitForTenantsDetailsToLoad();
    }

    public void chooseTenantFromCloudLicensesGrid(String tenant) {
        driver.click(How.XPATH, CLOUD_LICENSES_GRID_CHOOSE_TENANT_XPATH(tenant));
    }

    public void waitForTenantsToLoadInGrid() {
        driver.isElementPresentWithWait(How.XPATH, CLOUD_LICENSES_GRID_TENANT_LIST_XPATH(), 60);
    }

    public void waitForTenantsDetailsToLoad() {
        driver.isElementPresentWithWait(How.XPATH, TENANT_TIME_RANGE_SELECTOR_XPATH, 30);
    }
}
