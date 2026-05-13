package com.parabank.pageObjects;

import com.parabank.base.BasePage;
import com.parabank.interfaces.pageUIs.BillPayPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BillPayPageObject extends BasePage {

    public BillPayPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Enter payee name: {name}")
    public void enterPayeeName(String name) {
        sendKeyToElement(BillPayPageUI.PAYEE_NAME_INPUT, name);
    }

    @Step("Enter payee address: {address}")
    public void enterAddress(String address) {
        sendKeyToElement(BillPayPageUI.ADDRESS_INPUT, address);
    }

    @Step("Enter payee city: {city}")
    public void enterCity(String city) {
        sendKeyToElement(BillPayPageUI.CITY_INPUT, city);
    }

    @Step("Enter payee state: {state}")
    public void enterState(String state) {
        sendKeyToElement(BillPayPageUI.STATE_INPUT, state);
    }

    @Step("Enter payee zip code: {zipCode}")
    public void enterZipCode(String zipCode) {
        sendKeyToElement(BillPayPageUI.ZIP_CODE_INPUT, zipCode);
    }

    @Step("Enter payee phone: {phone}")
    public void enterPhone(String phone) {
        sendKeyToElement(BillPayPageUI.PHONE_INPUT, phone);
    }

    @Step("Enter payee account number: {accountNumber}")
    public void enterAccountNumber(String accountNumber) {
        sendKeyToElement(BillPayPageUI.ACCOUNT_NUMBER_INPUT, accountNumber);
    }

    @Step("Enter verify account number: {accountNumber}")
    public void enterVerifyAccount(String accountNumber) {
        sendKeyToElement(BillPayPageUI.VERIFY_ACCOUNT_INPUT, accountNumber);
    }

    @Step("Enter payment amount: {amount}")
    public void enterAmount(String amount) {
        sendKeyToElement(BillPayPageUI.AMOUNT_INPUT, amount);
    }

    @Step("Select from account by index: {index}")
    public void selectFromAccountByIndex(int index) {
        selectItemInDropdownByIndex(BillPayPageUI.FROM_ACCOUNT_SELECT, index);
    }

    @Step("Click Send Payment button")
    public void clickSendPayment() {
        clickToElement(BillPayPageUI.SEND_PAYMENT_BUTTON);
    }

    @Step("Pay bill to: {payeeName}, amount: {amount}")
    public void payBill(String payeeName, String address, String city,
                        String state, String zipCode, String phone,
                        String accountNumber, String amount) {
        enterPayeeName(payeeName);
        enterAddress(address);
        enterCity(city);
        enterState(state);
        enterZipCode(zipCode);
        enterPhone(phone);
        enterAccountNumber(accountNumber);
        enterVerifyAccount(accountNumber);
        enterAmount(amount);
        clickSendPayment();
    }

    public String getSuccessMessage() {
        waitForElementVisible(BillPayPageUI.SUCCESS_MESSAGE);
        return getElementText(BillPayPageUI.SUCCESS_MESSAGE);
    }

    public String getPaymentDetails() {
        waitForElementVisible(BillPayPageUI.PAYMENT_DETAILS);
        return getElementText(BillPayPageUI.PAYMENT_DETAILS);
    }

    public String getPageHeader() {
        waitForElementVisible(BillPayPageUI.PAGE_TITLE);
        return getElementText(BillPayPageUI.PAGE_TITLE);
    }
}
