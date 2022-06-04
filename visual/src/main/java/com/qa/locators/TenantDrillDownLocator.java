package com.qa.locators;

public class TenantDrillDownLocator {
    protected String TOTAL_USAGE_BTN_ID, MELT_BREAKDOWN_BTN_ID, LINEAR_VIEW_BTN_ID, LOGARITHMIC_VIEW_BTN_ID, AREA_CHART_BTN_ID, LICENSE_DETAILS_SECTION_XPATH;

    public TenantDrillDownLocator() {
        TOTAL_USAGE_BTN_ID = "total";
        MELT_BREAKDOWN_BTN_ID = "melt";
        LINEAR_VIEW_BTN_ID = "linear";
        LOGARITHMIC_VIEW_BTN_ID = "log";
        AREA_CHART_BTN_ID = "area";
        LICENSE_DETAILS_SECTION_XPATH = "//span[text()='License details']/ancestor::div[@class='card ']";
    }
}
