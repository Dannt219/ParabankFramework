package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pageObjects.AccountOverviewPageObject;
import com.parabank.pageObjects.BillPayPageObject;
import com.parabank.pageObjects.LoginPageObject;
import com.parabank.utils.ConfigReader;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("ParaBank Automation")
@Feature("Bill Pay")
public class BillPayTest extends BaseTest {

    private BillPayPageObject billPayPage;

    @BeforeMethod(alwaysRun = true)
    public void loginAndNavigate() {
        LoginPageObject loginPage = new LoginPageObject(getDriver());
        AccountOverviewPageObject overviewPage = loginPage.loginAs(
                ConfigReader.getUsername(), ConfigReader.getPassword());
        billPayPage = overviewPage.goToBillPay();
    }

    @Test(groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Story("Valid Bill Payment")
    @Description("Verify successful bill payment")
    public void testValidBillPayment() {
        billPayPage.payBill(
                "John Smith",
                "123 Main St",
                "New York",
                "NY",
                "10001",
                "212-555-1234",
                "12345",
                "50.00"
        );

        String successMessage = billPayPage.getSuccessMessage();
        Assert.assertTrue(
                successMessage.contains("Bill Payment Complete") || successMessage.contains("Complete"),
                "Success message should indicate bill payment completion");
    }

    @Test(groups = {"regression"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Bill Pay Page Display")
    @Description("Verify bill pay page is displayed correctly")
    public void testBillPayPageDisplayed() {
        String header = billPayPage.getPageHeader();
        Assert.assertEquals(header, "Bill Pay",
                "Page header should be 'Bill Pay'");
    }
}
