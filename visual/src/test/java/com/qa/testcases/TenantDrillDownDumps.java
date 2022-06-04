package com.qa.testcases;

import com.galenframework.api.Galen;
import com.galenframework.api.GalenPageDump;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import com.qa.clients.SeleniumDriver;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.TenantDrillDownPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TenantDrillDownDumps {
    private SeleniumDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private TenantDrillDownPage tenantDrillDownPage;

    @BeforeClass
    public void setUp() throws InterruptedException {
        driver = new SeleniumDriver("Chrome", "https://accounts-staging.saas.appd-test.com");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        tenantDrillDownPage = new TenantDrillDownPage(driver);
        loginPage.login("lev-c0-tenants-user1@yopmail.com", "Welcome@123");
        Thread.sleep(5000);
        driver.get("https://accounts-staging.saas.appd-test.com/license-usage/cloud-based/9682a038-85c8-4b30-bba8-51e64660f5c8");
//        homePage.navigateToRightMenu("Entitlements");
//        homePage.navigateToExpandedRightSubMenu("License Management");
//        homePage.searchTenant("lic-lev-c1-alameda-tenant");
//        homePage.chooseTenantFromCloudLicensesGrid("lic-lev-c1-alameda-tenant");
        homePage.waitForTenantsDetailsToLoad();
        Thread.sleep(5000);
    }

    @Test(priority = 1, enabled = false)
    public void createTokenConsumptionSectionDump() throws IOException, InterruptedException {
        tenantDrillDownPage.scrollToLicenseDetailsSection();
        Thread.sleep(3000);
        new GalenPageDump("Tenant Drill-Down Page").dumpPage(driver.getDriver(), "specs/tenants_drilldown_page.gspec", "target/dump");
    }

    @Test(priority = 2)
    public void createHistoricalUsageLogViewSectionDump() throws IOException, InterruptedException {
        tenantDrillDownPage.toggleUsageStyle("MELT BREAKDOWN");
        tenantDrillDownPage.toggleUsageView("LOGARITHMIC VIEW");
        new GalenPageDump("Tenant Drill-Down Page").dumpPage(driver.getDriver(), "specs/tenants_drilldown_page.gspec", "target/dump");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
