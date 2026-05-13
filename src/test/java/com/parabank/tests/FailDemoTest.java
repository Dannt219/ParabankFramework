package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pageObjects.LoginPageObject;
import com.parabank.pageObjects.AccountOverviewPageObject;
import com.parabank.utils.ConfigReader;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("ParaBank Automation")
@Feature("Demo Failure")
public class FailDemoTest extends BaseTest {

    private LoginPageObject loginPage;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        loginPage = new LoginPageObject(getDriver());
    }

    @Test(groups = {"demo"})
    @Severity(SeverityLevel.CRITICAL)
    @Story("Intentional Failure - Wrong Page Title")
    @Description("This test intentionally fails to demonstrate screenshot capture on failure in Allure report")
    public void testIntentionalFailure_WrongPageTitle() {
        // Login successfully
        AccountOverviewPageObject overviewPage = loginPage.loginAs(
                ConfigReader.getUsername(), ConfigReader.getPassword());

        // Assert something that will FAIL on purpose
        Assert.assertEquals(overviewPage.getPageHeader(), "THIS WILL NEVER MATCH",
                "Intentional failure: expected wrong page title to demonstrate screenshot on failure");
    }

    @Test(groups = {"demo"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Passing Test for Comparison")
    @Description("This test passes normally - for comparison with the failed test in report")
    public void testPassingForComparison() {
        AccountOverviewPageObject overviewPage = loginPage.loginAs(
                ConfigReader.getUsername(), ConfigReader.getPassword());

        Assert.assertTrue(overviewPage.isLoggedIn(),
                "User should be logged in");
    }
}
