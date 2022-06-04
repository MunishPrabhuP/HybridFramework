package com.qa.testcases;

import com.galenframework.api.Galen;
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

public class TenantDrillDownVisualCases {
    private SeleniumDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private TenantDrillDownPage tenantDrillDownPage;
    private List<GalenTestInfo> galenTestList;

    @BeforeClass
    public void setUp() throws InterruptedException {
        driver = new SeleniumDriver("Chrome", "https://accounts-staging.saas.appd-test.com");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        tenantDrillDownPage = new TenantDrillDownPage(driver);
        galenTestList = new LinkedList<>();
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

    @Test(priority = 1)
    public void validateTokenConsumptionSummarySectionLayout() throws IOException {
        GalenTestInfo test = GalenTestInfo.fromString("Tenant Drill-Down Page: Token Consumption Summary");
        LayoutReport layoutReport = Galen.checkLayout(driver.getDriver(), "specs/tenants_drilldown_page.gspec", Arrays.asList("token_consumption"));
        test.getReport().layout(layoutReport, "Validate Token Consumption Summary Section");
        galenTestList.add(test);
    }

    @Test(priority = 2)
    public void validateTotalTokenUsageInLinearView() throws IOException {
        GalenTestInfo test = GalenTestInfo.fromString("Tenant Drill-Down Page: Historical Total Usage Linear View");
        LayoutReport layoutReport = Galen.checkLayout(driver.getDriver(), "specs/tenants_drilldown_page.gspec", Arrays.asList("total_usage_linear_view"));
        test.getReport().layout(layoutReport, "Validate Historical Total Usage in Linear View");
        galenTestList.add(test);
    }

    @Test(priority = 3)
    public void validateTotalTokenUsageInLogarithmicView() throws IOException, InterruptedException {
        GalenTestInfo test = GalenTestInfo.fromString("Tenant Drill-Down Page: Historical Total Usage Logarithmic View");
        tenantDrillDownPage.toggleUsageView("LOGARITHMIC VIEW");
        LayoutReport layoutReport = Galen.checkLayout(driver.getDriver(), "specs/tenants_drilldown_page.gspec", Arrays.asList("total_usage_log_view"));
        test.getReport().layout(layoutReport, "Validate Historical Total Usage in Logarithmic View");
        galenTestList.add(test);
    }

    @Test(priority = 4)
    public void validateMELTBreakdownInLinearView() throws IOException, InterruptedException {
        GalenTestInfo test = GalenTestInfo.fromString("Tenant Drill-Down Page: Historical MELT Breakdown Linear View");
        tenantDrillDownPage.toggleUsageStyle("MELT BREAKDOWN");
        tenantDrillDownPage.toggleUsageView("LINEAR VIEW");
        LayoutReport layoutReport = Galen.checkLayout(driver.getDriver(), "specs/tenants_drilldown_page.gspec", Arrays.asList("melt_linear_view"));
        test.getReport().layout(layoutReport, "Validate Historical MELT Breakdown in Linear View");
        galenTestList.add(test);
    }

    @Test(priority = 5)
    public void validateMELTBreakdownInLogarithmicView() throws IOException, InterruptedException {
        GalenTestInfo test = GalenTestInfo.fromString("Tenant Drill-Down Page: Historical MELT Breakdown Logarithmic View");
        tenantDrillDownPage.toggleUsageView("LOGARITHMIC VIEW");
        LayoutReport layoutReport = Galen.checkLayout(driver.getDriver(), "specs/tenants_drilldown_page.gspec", Arrays.asList("melt_log_view"));
        test.getReport().layout(layoutReport, "Validate Historical MELT Breakdown in Logarithmic View");
        galenTestList.add(test);
    }

    @Test(priority = 6)
    public void validateLicenseDetailsSectionLayout() throws IOException, InterruptedException {
        GalenTestInfo test = GalenTestInfo.fromString("Tenant Drill-Down Page: License Details");
        tenantDrillDownPage.scrollToLicenseDetailsSection();
        Thread.sleep(1000);
        LayoutReport layoutReport = Galen.checkLayout(driver.getDriver(), "specs/tenants_drilldown_page.gspec", Arrays.asList("license_details"));
        test.getReport().layout(layoutReport, "Validate License Details Section");
        galenTestList.add(test);
    }

    @AfterClass
    public void tearDown() throws IOException {
        new HtmlReportBuilder().build(galenTestList, "target/galen-html-reports");
        driver.quit();
    }
}
