package com.parabank.pageObjects;

import com.parabank.base.BasePage;
import com.parabank.interfaces.pageUIs.OpenAccountPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class OpenAccountPageObject extends BasePage {

    public OpenAccountPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Select account type: {accountType}")
    public void selectAccountType(String accountType) {
        selectItemInDropdownByText(OpenAccountPageUI.ACCOUNT_TYPE_SELECT, accountType);
    }

    @Step("Select from account by index: {index}")
    public void selectFromAccountByIndex(int index) {
        selectItemInDropdownByIndex(OpenAccountPageUI.FROM_ACCOUNT_SELECT, index);
    }

    @Step("Click Open New Account button")
    public void clickOpenAccount() {
        clickToElement(OpenAccountPageUI.OPEN_ACCOUNT_BUTTON);
    }

    @Step("Open new {accountType} account")
    public void openNewAccount(String accountType, int fromAccountIndex) {
        selectAccountType(accountType);
        selectFromAccountByIndex(fromAccountIndex);
        clickOpenAccount();
    }

    public String getSuccessMessage() {
        waitForElementVisible(OpenAccountPageUI.SUCCESS_MESSAGE);
        return getElementText(OpenAccountPageUI.SUCCESS_MESSAGE);
    }

    public String getNewAccountId() {
        waitForElementVisible(OpenAccountPageUI.NEW_ACCOUNT_ID);
        return getElementText(OpenAccountPageUI.NEW_ACCOUNT_ID);
    }

    public String getPageHeader() {
        waitForElementVisible(OpenAccountPageUI.PAGE_TITLE);
        return getElementText(OpenAccountPageUI.PAGE_TITLE);
    }
}
