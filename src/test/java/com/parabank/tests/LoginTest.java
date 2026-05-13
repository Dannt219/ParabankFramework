package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pageObjects.AccountOverviewPageObject;
import com.parabank.pageObjects.LoginPageObject;
import com.parabank.tests.dataproviders.LoginDataProvider;
import com.parabank.utils.ConfigReader;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("ParaBank Automation")
@Feature("Login")
public class LoginTest extends BaseTest {

    private LoginPageObject loginPage;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        loginPage = new LoginPageObject(getDriver());
    }

    @Test(groups = {"smoke", "regression"})
    @Severity(SeverityLevel.BLOCKER)
    @Story("Valid Login")
    @Description("Verify user can login with valid credentials")
    public void testValidLogin() {
        AccountOverviewPageObject overviewPage = loginPage.loginAs(
                ConfigReader.getUsername(), ConfigReader.getPassword());

        Assert.assertTrue(overviewPage.isLoggedIn(),
                "User should be logged in after valid login");
    }

    @Test(groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Story("Logout")
    @Description("Verify user can logout successfully")
    public void testLogout() {
        AccountOverviewPageObject overviewPage = loginPage.loginAs(
                ConfigReader.getUsername(), ConfigReader.getPassword());
        LoginPageObject loggedOutPage = overviewPage.logout();

        Assert.assertTrue(loggedOutPage.isLoginPageDisplayed(),
                "Login page should be displayed after logout");
    }

    @Test(groups = {"negative", "regression"}, dataProvider = "invalidLoginData",
            dataProviderClass = LoginDataProvider.class)
    @Severity(SeverityLevel.NORMAL)
    @Story("Invalid Login")
    @Description("Verify error message is displayed for invalid credentials")
    public void testInvalidLogin(String username, String password, String expectedError) {
        loginPage.loginWithInvalidCredentials(username, password);

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should be displayed for invalid login");
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError),
                "Error message should contain: " + expectedError);
    }

    @Test(groups = {"negative", "regression"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Empty Credentials Login")
    @Description("Verify error message when logging in with empty credentials")
    public void testEmptyCredentialsLogin() {
        loginPage.loginWithInvalidCredentials("", "");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should be displayed for empty credentials");
    }
}
