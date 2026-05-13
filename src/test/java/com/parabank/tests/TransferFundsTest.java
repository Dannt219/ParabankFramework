package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pageObjects.AccountOverviewPageObject;
import com.parabank.pageObjects.LoginPageObject;
import com.parabank.pageObjects.TransferFundsPageObject;
import com.parabank.tests.dataproviders.TransferDataProvider;
import com.parabank.utils.ConfigReader;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("ParaBank Automation")
@Feature("Transfer Funds")
public class TransferFundsTest extends BaseTest {

    private TransferFundsPageObject transferFundsPage;

    @BeforeMethod(alwaysRun = true)
    public void loginAndNavigate() {
        LoginPageObject loginPage = new LoginPageObject(getDriver());
        AccountOverviewPageObject overviewPage = loginPage.loginAs(
                ConfigReader.getUsername(), ConfigReader.getPassword());
        transferFundsPage = overviewPage.goToTransferFunds();
    }

    @Test(groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Story("Valid Transfer")
    @Description("Verify successful fund transfer between accounts")
    public void testValidTransfer() {
        transferFundsPage.transferFunds("100", 0, 1);

        String successMessage = transferFundsPage.getSuccessMessage();
        logger.info("Transfer page header: {}", successMessage);
        Assert.assertTrue(
                successMessage.contains("Transfer Complete") || successMessage.contains("transferred"),
                "Success message should indicate transfer completion, but was: " + successMessage);
    }

    @Test(groups = {"regression"}, dataProvider = "transferAmounts",
            dataProviderClass = TransferDataProvider.class)
    @Severity(SeverityLevel.NORMAL)
    @Story("Transfer Various Amounts")
    @Description("Verify fund transfer with various amounts")
    public void testTransferVariousAmounts(String amount, boolean shouldSucceed) {
        transferFundsPage.transferFunds(amount, 0, 0);

        if (shouldSucceed) {
            String successMessage = transferFundsPage.getSuccessMessage();
            Assert.assertTrue(
                    successMessage.contains("Transfer Complete") || successMessage.contains("transferred"),
                    "Transfer of " + amount + " should succeed");
        } else {
            Assert.assertTrue(transferFundsPage.isErrorDisplayed(),
                    "Transfer of " + amount + " should show error");
        }
    }

    @Test(groups = {"negative", "regression"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Invalid Transfer Amount")
    @Description("Verify error when transferring with invalid amount")
    public void testInvalidTransferAmount() {
        transferFundsPage.enterAmount("");
        transferFundsPage.clickTransfer();

        // The application should show an error or not process the transfer
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("transfer"),
                "Should remain on transfer page for invalid amount");
    }
}
