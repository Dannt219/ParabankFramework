package com.parabank.pageObjects;

import com.parabank.base.BasePage;
import com.parabank.interfaces.pageUIs.RequestLoanPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class RequestLoanPageObject extends BasePage {

    public RequestLoanPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Enter loan amount: {amount}")
    public void enterLoanAmount(String amount) {
        sendKeyToElement(RequestLoanPageUI.LOAN_AMOUNT_INPUT, amount);
    }

    @Step("Enter down payment: {downPayment}")
    public void enterDownPayment(String downPayment) {
        sendKeyToElement(RequestLoanPageUI.DOWN_PAYMENT_INPUT, downPayment);
    }

    @Step("Select from account by index: {index}")
    public void selectFromAccountByIndex(int index) {
        selectItemInDropdownByIndex(RequestLoanPageUI.FROM_ACCOUNT_SELECT, index);
    }

    @Step("Click Apply Now button")
    public void clickApply() {
        clickToElement(RequestLoanPageUI.APPLY_BUTTON);
    }

    @Step("Request loan: amount={amount}, downPayment={downPayment}")
    public void requestLoan(String amount, String downPayment, int fromAccountIndex) {
        enterLoanAmount(amount);
        enterDownPayment(downPayment);
        selectFromAccountByIndex(fromAccountIndex);
        clickApply();
    }

    public String getLoanStatus() {
        waitForElementVisible(RequestLoanPageUI.APPROVED_MESSAGE);
        return getElementText(RequestLoanPageUI.APPROVED_MESSAGE);
    }

    public boolean isLoanApproved() {
        String status = getLoanStatus();
        return status.toLowerCase().contains("approved");
    }

    public boolean isLoanDenied() {
        return isElementDisplayed(RequestLoanPageUI.DENIED_MESSAGE);
    }

    public String getResultMessage() {
        waitForElementVisible(RequestLoanPageUI.RESULT_MESSAGE);
        return getElementText(RequestLoanPageUI.RESULT_MESSAGE);
    }

    public String getNewAccountId() {
        waitForElementVisible(RequestLoanPageUI.NEW_ACCOUNT_ID);
        return getElementText(RequestLoanPageUI.NEW_ACCOUNT_ID);
    }

    public String getPageHeader() {
        waitForElementVisible(RequestLoanPageUI.PAGE_TITLE);
        return getElementText(RequestLoanPageUI.PAGE_TITLE);
    }
}
