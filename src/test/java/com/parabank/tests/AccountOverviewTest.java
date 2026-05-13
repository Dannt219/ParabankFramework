package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pageObjects.AccountOverviewPageObject;
import com.parabank.pageObjects.LoginPageObject;
import com.parabank.utils.ConfigReader;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

@Epic("ParaBank Automation")
@Feature("Account Overview")
public class AccountOverviewTest extends BaseTest {

    private AccountOverviewPageObject accountOverviewPage;

    @BeforeMethod(alwaysRun = true)
    public void loginAndNavigate() {
        LoginPageObject loginPage = new LoginPageObject(getDriver());
        accountOverviewPage = loginPage.loginAs(
                ConfigReader.getUsername(), ConfigReader.getPassword());
    }

    @Test(groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Story("View Account Overview")
    @Description("Verify accounts overview page displays account information")
    public void testAccountOverviewDisplayed() {
        Assert.assertEquals(accountOverviewPage.getPageHeader(), "Accounts Overview",
                "Page header should be 'Accounts Overview'");
        Assert.assertTrue(accountOverviewPage.isAccountsTableDisplayed(),
                "Accounts table should be displayed");
    }

    @Test(groups = {"smoke", "regression"})
    @Severity(SeverityLevel.NORMAL)
    @Story("View Account Numbers")
    @Description("Verify at least one account is displayed in the overview")
    public void testAccountNumbersDisplayed() {
        List<String> accountNumbers = accountOverviewPage.getAccountNumbers();

        Assert.assertFalse(accountNumbers.isEmpty(),
                "At least one account should be displayed");
        logger.info("Found {} accounts", accountNumbers.size());
    }

    @Test(groups = {"regression"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigate from Account Overview")
    @Description("Verify navigation to Transfer Funds from Account Overview")
    public void testNavigateToTransferFunds() {
        accountOverviewPage.goToTransferFunds();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("transfer"),
                "Should navigate to Transfer Funds page");
    }
}
