package com.parabank.pageObjects;

import com.parabank.base.BasePage;
import com.parabank.interfaces.pageUIs.TransferFundsPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class TransferFundsPageObject extends BasePage {

    public TransferFundsPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Enter transfer amount: {amount}")
    public void enterAmount(String amount) {
        sendKeyToElement(TransferFundsPageUI.AMOUNT_INPUT, amount);
    }

    @Step("Select from account: {accountId}")
    public void selectFromAccount(String accountId) {
        selectItemInDropdownByValue(TransferFundsPageUI.FROM_ACCOUNT_SELECT, accountId);
    }

    @Step("Select from account by index: {index}")
    public void selectFromAccountByIndex(int index) {
        selectItemInDropdownByIndex(TransferFundsPageUI.FROM_ACCOUNT_SELECT, index);
    }

    @Step("Select to account: {accountId}")
    public void selectToAccount(String accountId) {
        selectItemInDropdownByValue(TransferFundsPageUI.TO_ACCOUNT_SELECT, accountId);
    }

    @Step("Select to account by index: {index}")
    public void selectToAccountByIndex(int index) {
        selectItemInDropdownByIndex(TransferFundsPageUI.TO_ACCOUNT_SELECT, index);
    }

    @Step("Click Transfer button")
    public void clickTransfer() {
        clickToElement(TransferFundsPageUI.TRANSFER_BUTTON);
    }

    @Step("Transfer funds: amount={amount}")
    public void transferFunds(String amount, int fromIndex, int toIndex) {
        enterAmount(amount);
        selectFromAccountByIndex(fromIndex);
        selectToAccountByIndex(toIndex);
        clickTransfer();
    }

    public String getSuccessMessage() {
        waitForElementVisible(TransferFundsPageUI.SUCCESS_MESSAGE);
        return getElementText(TransferFundsPageUI.SUCCESS_MESSAGE);
    }

    public String getTransferDetails() {
        waitForElementVisible(TransferFundsPageUI.TRANSFER_DETAILS);
        return getElementText(TransferFundsPageUI.TRANSFER_DETAILS);
    }

    public boolean isErrorDisplayed() {
        return isElementDisplayed(TransferFundsPageUI.ERROR_MESSAGE);
    }

    public String getErrorMessage() {
        return getElementText(TransferFundsPageUI.ERROR_MESSAGE);
    }

    public String getPageHeader() {
        waitForElementVisible(TransferFundsPageUI.PAGE_TITLE);
        return getElementText(TransferFundsPageUI.PAGE_TITLE);
    }
}
