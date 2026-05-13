package com.parabank.pageObjects;

import com.parabank.base.BasePage;
import com.parabank.interfaces.pageUIs.RequestLoanPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class RequestLoanPageObject extends BasePage {

    private WebDriver driver;

    public RequestLoanPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Enter loan amount: {amount}")
    public void enterLoanAmount(String amount) {
        sendKeyToElement(driver, RequestLoanPageUI.LOAN_AMOUNT_INPUT, amount);
    }

    @Step("Enter down payment: {downPayment}")
    public void enterDownPayment(String downPayment) {
        sendKeyToElement(driver, RequestLoanPageUI.DOWN_PAYMENT_INPUT, downPayment);
    }

    @Step("Select from account by index: {index}")
    public void selectFromAccountByIndex(int index) {
        selectItemInDropdownByIndex(driver, RequestLoanPageUI.FROM_ACCOUNT_SELECT, index);
    }

    @Step("Click Apply Now button")
    public void clickApply() {
        clickToElement(driver, RequestLoanPageUI.APPLY_BUTTON);
    }

    @Step("Request loan: amount={amount}, downPayment={downPayment}")
    public void requestLoan(String amount, String downPayment, int fromAccountIndex) {
        enterLoanAmount(amount);
        enterDownPayment(downPayment);
        selectFromAccountByIndex(fromAccountIndex);
        clickApply();
    }

    public String getLoanStatus() {
        waitForElementVisible(driver, RequestLoanPageUI.APPROVED_MESSAGE);
        return getElementText(driver, RequestLoanPageUI.APPROVED_MESSAGE);
    }

    public boolean isLoanApproved() {
        String status = getLoanStatus();
        return status.toLowerCase().contains("approved");
    }

    public boolean isLoanDenied() {
        return isElementDisplayed(driver, RequestLoanPageUI.DENIED_MESSAGE);
    }

    public String getResultMessage() {
        waitForElementVisible(driver, RequestLoanPageUI.RESULT_MESSAGE);
        return getElementText(driver, RequestLoanPageUI.RESULT_MESSAGE);
    }

    public String getNewAccountId() {
        waitForElementVisible(driver, RequestLoanPageUI.NEW_ACCOUNT_ID);
        return getElementText(driver, RequestLoanPageUI.NEW_ACCOUNT_ID);
    }

    public String getPageHeader() {
        waitForElementVisible(driver, RequestLoanPageUI.PAGE_TITLE);
        return getElementText(driver, RequestLoanPageUI.PAGE_TITLE);
    }
}
