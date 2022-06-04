package com.qa.locators;

public class HomeLocator {
    protected String CLOUD_LICENSES_SEARCH_TENANT_ID, TENANT_TIME_RANGE_SELECTOR_XPATH;

    public HomeLocator() {
        CLOUD_LICENSES_SEARCH_TENANT_ID = "search-input";
        TENANT_TIME_RANGE_SELECTOR_XPATH = "//span[@test-id='time-range-selector-button']";
    }

    protected String RIGHT_NAVIGATION_MENU_XPATH(String menu) {
        return "//span[@class='particle-navbar__navItem_label'][text()='" + menu + "']";
    }

    protected String RIGHT_NAVIGATION_SUB_MENU_XPATH(String menu) {
        return "//a[@test-id='nav-bar-secondary']/span[text()='" + menu + "']";
    }

    protected String CLOUD_LICENSES_GRID_CHOOSE_TENANT_XPATH(String tenant) {
        return CLOUD_LICENSES_GRID_TENANT_LIST_XPATH() + "//span[text()='" + tenant + "']";
    }

    protected String CLOUD_LICENSES_GRID_TENANT_LIST_XPATH() {
        return "//div[@col-id='tenantName']/div[@test-id='text-cell']";
    }
}
