package com.qa.pages;

import com.qa.clients.SeleniumDriver;
import com.qa.locators.TenantDrillDownLocator;
import org.openqa.selenium.support.How;

public class TenantDrillDownPage extends TenantDrillDownLocator {
    SeleniumDriver driver;

    public TenantDrillDownPage(SeleniumDriver driver) {
        this.driver = driver;
    }

    public void toggleUsageStyle(String style) throws InterruptedException {
        switch (style) {
            case "TOTAL USAGE":
                driver.click(How.ID, TOTAL_USAGE_BTN_ID);
                break;
            case "MELT BREAKDOWN":
                driver.click(How.ID, MELT_BREAKDOWN_BTN_ID);
                break;
        }
        Thread.sleep(3000);
    }

    public void toggleUsageView(String view) throws InterruptedException {
        switch (view) {
            case "LINEAR VIEW":
                driver.click(How.ID, LINEAR_VIEW_BTN_ID);
                break;
            case "LOGARITHMIC VIEW":
                driver.click(How.ID, LOGARITHMIC_VIEW_BTN_ID);
                break;
            case "AREA CHART":
                driver.click(How.ID, AREA_CHART_BTN_ID);
                break;
        }
        Thread.sleep(3000);
    }

    public void scrollToLicenseDetailsSection() {
        driver.scroll(How.XPATH, LICENSE_DETAILS_SECTION_XPATH);
    }
}
