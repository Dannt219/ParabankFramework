package com.parabank.pageObjects;

import com.parabank.base.BasePage;
import com.parabank.interfaces.pageUIs.RegisterPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class RegisterPageObject extends BasePage {

    public RegisterPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Enter first name: {firstName}")
    public void enterFirstName(String firstName) {
        sendKeyToElement(RegisterPageUI.FIRST_NAME_INPUT, firstName);
    }

    @Step("Enter last name: {lastName}")
    public void enterLastName(String lastName) {
        sendKeyToElement(RegisterPageUI.LAST_NAME_INPUT, lastName);
    }

    @Step("Enter address: {address}")
    public void enterAddress(String address) {
        sendKeyToElement(RegisterPageUI.ADDRESS_INPUT, address);
    }

    @Step("Enter city: {city}")
    public void enterCity(String city) {
        sendKeyToElement(RegisterPageUI.CITY_INPUT, city);
    }

    @Step("Enter state: {state}")
    public void enterState(String state) {
        sendKeyToElement(RegisterPageUI.STATE_INPUT, state);
    }

    @Step("Enter zip code: {zipCode}")
    public void enterZipCode(String zipCode) {
        sendKeyToElement(RegisterPageUI.ZIP_CODE_INPUT, zipCode);
    }

    @Step("Enter phone: {phone}")
    public void enterPhone(String phone) {
        sendKeyToElement(RegisterPageUI.PHONE_INPUT, phone);
    }

    @Step("Enter SSN: {ssn}")
    public void enterSsn(String ssn) {
        sendKeyToElement(RegisterPageUI.SSN_INPUT, ssn);
    }

    @Step("Enter username: {username}")
    public void enterUsername(String username) {
        sendKeyToElement(RegisterPageUI.USERNAME_INPUT, username);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        sendKeyToElement(RegisterPageUI.PASSWORD_INPUT, password);
    }

    @Step("Enter confirm password")
    public void enterConfirmPassword(String confirmPassword) {
        sendKeyToElement(RegisterPageUI.CONFIRM_PASSWORD_INPUT, confirmPassword);
    }

    @Step("Click Register button")
    public void clickRegister() {
        clickToElement(RegisterPageUI.REGISTER_BUTTON);
    }

    @Step("Register new customer")
    public void registerCustomer(String firstName, String lastName, String address,
                                 String city, String state, String zipCode,
                                 String phone, String ssn, String username,
                                 String password) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterAddress(address);
        enterCity(city);
        enterState(state);
        enterZipCode(zipCode);
        enterPhone(phone);
        enterSsn(ssn);
        enterUsername(username);
        enterPassword(password);
        enterConfirmPassword(password);
        clickRegister();
    }

    public String getSuccessTitle() {
        waitForElementVisible(RegisterPageUI.SUCCESS_MESSAGE);
        return getElementText(RegisterPageUI.SUCCESS_MESSAGE);
    }

    public String getWelcomeMessage() {
        waitForElementVisible(RegisterPageUI.WELCOME_MESSAGE);
        return getElementText(RegisterPageUI.WELCOME_MESSAGE);
    }

    public boolean isErrorDisplayed() {
        return isElementDisplayed(RegisterPageUI.ERROR_MESSAGES);
    }
}
