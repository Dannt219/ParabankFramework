package com.parabank.pageObjects;

import com.parabank.base.BasePage;
import com.parabank.interfaces.pageUIs.LoginPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage {

    public LoginPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Enter username: {username}")
    public void enterUsername(String username) {
        waitForElementVisible(LoginPageUI.USERNAME_INPUT);
        sendKeyToElement(LoginPageUI.USERNAME_INPUT, username);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        waitForElementVisible(LoginPageUI.PASSWORD_INPUT);
        sendKeyToElement(LoginPageUI.PASSWORD_INPUT, password);
    }

    @Step("Click Login button")
    public void clickLoginButton() {
        waitForElementClickable(LoginPageUI.LOGIN_BUTTON);
        clickToElement(LoginPageUI.LOGIN_BUTTON);
    }

    @Step("Login with credentials: {username}")
    public AccountOverviewPageObject loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        waitHelper.waitForPageLoad();
        return new AccountOverviewPageObject(driver);
    }

    @Step("Login with invalid credentials: {username}")
    public LoginPageObject loginWithInvalidCredentials(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return this;
    }

    @Step("Get error message")
    public String getErrorMessage() {
        waitForElementVisible(LoginPageUI.ERROR_MESSAGE);
        return getElementText(LoginPageUI.ERROR_MESSAGE);
    }

    public boolean isErrorDisplayed() {
        return isElementDisplayed(LoginPageUI.ERROR_MESSAGE);
    }

    @Step("Click Register link")
    public RegisterPageObject clickRegister() {
        clickToElement(LoginPageUI.REGISTER_LINK);
        return new RegisterPageObject(driver);
    }

    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(LoginPageUI.USERNAME_INPUT)
                && isElementDisplayed(LoginPageUI.PASSWORD_INPUT);
    }
}
