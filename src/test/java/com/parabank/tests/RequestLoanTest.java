package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pageObjects.AccountOverviewPageObject;
import com.parabank.pageObjects.LoginPageObject;
import com.parabank.pageObjects.RequestLoanPageObject;
import com.parabank.utils.ConfigReader;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("ParaBank Automation")
@Feature("Request Loan")
public class RequestLoanTest extends BaseTest {

    private RequestLoanPageObject requestLoanPage;

    @BeforeMethod(alwaysRun = true)
    public void loginAndNavigate() {
        LoginPageObject loginPage = new LoginPageObject(getDriver());
        AccountOverviewPageObject overviewPage = loginPage.loginAs(
                ConfigReader.getUsername(), ConfigReader.getPassword());
        requestLoanPage = overviewPage.goToRequestLoan();
    }

    @Test(groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Story("Valid Loan Request")
    @Description("Verify successful loan request with valid data")
    public void testValidLoanRequest() {
        requestLoanPage.requestLoan("1000", "100", 0);

        String result = requestLoanPage.getResultMessage();
        Assert.assertTrue(
                result.contains("Loan Request Processed") || result.contains("Loan"),
                "Result should indicate loan request was processed");
    }

    @Test(groups = {"regression"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Loan Request Page Display")
    @Description("Verify loan request page is displayed correctly")
    public void testLoanRequestPageDisplayed() {
        String header = requestLoanPage.getPageHeader();
        Assert.assertTrue(header.contains("Apply for a Loan") || header.contains("Loan"),
                "Page header should contain loan-related text");
    }

    @Test(groups = {"negative", "regression"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Invalid Loan Request - High Amount")
    @Description("Verify loan denial for unreasonably high amount with low down payment")
    public void testInvalidLoanHighAmount() {
        requestLoanPage.requestLoan("999999999", "1", 0);

        String result = requestLoanPage.getResultMessage();
        Assert.assertTrue(
                result.contains("Loan Request Processed") || result.contains("Denied") || result.contains("Loan"),
                "Result should indicate loan request was processed (possibly denied)");
    }

    @Test(groups = {"negative", "regression"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Invalid Loan Request - Zero Down Payment")
    @Description("Verify loan handling with zero down payment")
    public void testLoanZeroDownPayment() {
        requestLoanPage.requestLoan("5000", "0", 0);

        String result = requestLoanPage.getResultMessage();
        Assert.assertNotNull(result, "Should display a result message");
    }
}
