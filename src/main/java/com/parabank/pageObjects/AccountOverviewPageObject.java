package com.parabank.pageObjects;

import com.parabank.base.BasePage;
import com.parabank.interfaces.pageUIs.AccountOverviewPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AccountOverviewPageObject extends BasePage {

    private WebDriver driver;

    public AccountOverviewPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Get page header")
    public String getPageHeader() {
        waitHelper.waitForPageLoad();
        waitForElementVisible(driver, AccountOverviewPageUI.PAGE_TITLE);
        return getElementText(driver, AccountOverviewPageUI.PAGE_TITLE);
    }

    @Step("Check if accounts table is displayed")
    public boolean isAccountsTableDisplayed() {
        return isElementDisplayed(driver, AccountOverviewPageUI.ACCOUNTS_TABLE);
    }

    @Step("Get list of account numbers")
    public List<String> getAccountNumbers() {
        waitForAllElementsVisible(driver, AccountOverviewPageUI.ACCOUNT_LINKS);
        List<WebElement> accounts = getElements(driver, AccountOverviewPageUI.ACCOUNT_LINKS);
        return accounts.stream()
                .map(WebElement::getText)
                .filter(text -> !text.isEmpty())
                .toList();
    }

    @Step("Get first account number")
    public String getFirstAccountNumber() {
        List<String> accounts = getAccountNumbers();
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Step("Click Logout")
    public LoginPageObject logout() {
        clickToElement(driver, AccountOverviewPageUI.LOGOUT_LINK);
        return new LoginPageObject(driver);
    }

    @Step("Navigate to Transfer Funds")
    public TransferFundsPageObject goToTransferFunds() {
        clickToElement(driver, AccountOverviewPageUI.TRANSFER_FUNDS_LINK);
        return new TransferFundsPageObject(driver);
    }

    @Step("Navigate to Bill Pay")
    public BillPayPageObject goToBillPay() {
        clickToElement(driver, AccountOverviewPageUI.BILL_PAY_LINK);
        return new BillPayPageObject(driver);
    }

    @Step("Navigate to Find Transactions")
    public FindTransactionsPageObject goToFindTransactions() {
        clickToElement(driver, AccountOverviewPageUI.FIND_TRANSACTIONS_LINK);
        return new FindTransactionsPageObject(driver);
    }

    @Step("Navigate to Request Loan")
    public RequestLoanPageObject goToRequestLoan() {
        clickToElement(driver, AccountOverviewPageUI.REQUEST_LOAN_LINK);
        return new RequestLoanPageObject(driver);
    }

    @Step("Navigate to Open New Account")
    public OpenAccountPageObject goToOpenAccount() {
        clickToElement(driver, AccountOverviewPageUI.OPEN_ACCOUNT_LINK);
        return new OpenAccountPageObject(driver);
    }

    @Step("Navigate to Update Contact Info")
    public void goToUpdateContact() {
        clickToElement(driver, AccountOverviewPageUI.UPDATE_CONTACT_LINK);
    }

    @Step("Navigate to Accounts Overview")
    public AccountOverviewPageObject goToAccountsOverview() {
        clickToElement(driver, AccountOverviewPageUI.ACCOUNTS_OVERVIEW_LINK);
        return this;
    }

    public boolean isLoggedIn() {
        return isElementDisplayed(driver, AccountOverviewPageUI.LOGOUT_LINK);
    }
}
