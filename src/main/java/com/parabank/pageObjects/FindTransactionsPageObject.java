package com.parabank.pageObjects;

import com.parabank.base.BasePage;
import com.parabank.interfaces.pageUIs.FindTransactionsPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FindTransactionsPageObject extends BasePage {

    private WebDriver driver;

    public FindTransactionsPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Select account by index: {index}")
    public void selectAccountByIndex(int index) {
        selectItemInDropdownByIndex(driver, FindTransactionsPageUI.ACCOUNT_SELECT, index);
    }

    @Step("Find transaction by ID: {transactionId}")
    public void findByTransactionId(String transactionId) {
        sendKeyToElement(driver, FindTransactionsPageUI.FIND_BY_ID_INPUT, transactionId);
        clickToElement(driver, FindTransactionsPageUI.FIND_BY_ID_BUTTON);
    }

    @Step("Find transactions by date: {date}")
    public void findByDate(String date) {
        sendKeyToElement(driver, FindTransactionsPageUI.FIND_BY_DATE_INPUT, date);
        clickToElement(driver, FindTransactionsPageUI.FIND_BY_DATE_BUTTON);
    }

    @Step("Find transactions by date range: {fromDate} to {toDate}")
    public void findByDateRange(String fromDate, String toDate) {
        sendKeyToElement(driver, FindTransactionsPageUI.FIND_BY_DATE_RANGE_FROM_INPUT, fromDate);
        sendKeyToElement(driver, FindTransactionsPageUI.FIND_BY_DATE_RANGE_TO_INPUT, toDate);
        clickToElement(driver, FindTransactionsPageUI.FIND_BY_DATE_RANGE_BUTTON);
    }

    @Step("Find transactions by amount: {amount}")
    public void findByAmount(String amount) {
        sendKeyToElement(driver, FindTransactionsPageUI.FIND_BY_AMOUNT_INPUT, amount);
        clickToElement(driver, FindTransactionsPageUI.FIND_BY_AMOUNT_BUTTON);
    }

    public int getTransactionCount() {
        try {
            waitForAllElementsVisible(driver, FindTransactionsPageUI.TRANSACTION_RESULTS);
            List<WebElement> rows = getElements(driver, FindTransactionsPageUI.TRANSACTION_RESULTS);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isNoResultsDisplayed() {
        return isElementDisplayed(driver, FindTransactionsPageUI.NO_RESULTS_MESSAGE);
    }

    public String getPageHeader() {
        waitForElementVisible(driver, FindTransactionsPageUI.PAGE_TITLE);
        return getElementText(driver, FindTransactionsPageUI.PAGE_TITLE);
    }
}
