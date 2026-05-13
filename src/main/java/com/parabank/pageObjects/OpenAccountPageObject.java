package com.parabank.pageObjects;

import com.parabank.base.BasePage;
import com.parabank.interfaces.pageUIs.OpenAccountPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class OpenAccountPageObject extends BasePage {

    private WebDriver driver;

    public OpenAccountPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Select account type: {accountType}")
    public void selectAccountType(String accountType) {
        selectItemInDropdownByText(driver, OpenAccountPageUI.ACCOUNT_TYPE_SELECT, accountType);
    }

    @Step("Select from account by index: {index}")
    public void selectFromAccountByIndex(int index) {
        selectItemInDropdownByIndex(driver, OpenAccountPageUI.FROM_ACCOUNT_SELECT, index);
    }

    @Step("Click Open New Account button")
    public void clickOpenAccount() {
        clickToElement(driver, OpenAccountPageUI.OPEN_ACCOUNT_BUTTON);
    }

    @Step("Open new {accountType} account")
    public void openNewAccount(String accountType, int fromAccountIndex) {
        selectAccountType(accountType);
        selectFromAccountByIndex(fromAccountIndex);
        clickOpenAccount();
    }

    public String getSuccessMessage() {
        waitForElementVisible(driver, OpenAccountPageUI.SUCCESS_MESSAGE);
        return getElementText(driver, OpenAccountPageUI.SUCCESS_MESSAGE);
    }

    public String getNewAccountId() {
        waitForElementVisible(driver, OpenAccountPageUI.NEW_ACCOUNT_ID);
        return getElementText(driver, OpenAccountPageUI.NEW_ACCOUNT_ID);
    }

    public String getPageHeader() {
        waitForElementVisible(driver, OpenAccountPageUI.PAGE_TITLE);
        return getElementText(driver, OpenAccountPageUI.PAGE_TITLE);
    }
}
