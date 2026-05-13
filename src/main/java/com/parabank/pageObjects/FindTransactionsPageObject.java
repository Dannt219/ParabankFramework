package com.parabank.pageObjects;

import com.parabank.base.BasePage;
import com.parabank.interfaces.pageUIs.FindTransactionsPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FindTransactionsPageObject extends BasePage {

    public FindTransactionsPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Select account by index: {index}")
    public void selectAccountByIndex(int index) {
        selectItemInDropdownByIndex(FindTransactionsPageUI.ACCOUNT_SELECT, index);
    }

    @Step("Find transaction by ID: {transactionId}")
    public void findByTransactionId(String transactionId) {
        sendKeyToElement(FindTransactionsPageUI.FIND_BY_ID_INPUT, transactionId);
        clickToElement(FindTransactionsPageUI.FIND_BY_ID_BUTTON);
    }

    @Step("Find transactions by date: {date}")
    public void findByDate(String date) {
        sendKeyToElement(FindTransactionsPageUI.FIND_BY_DATE_INPUT, date);
        clickToElement(FindTransactionsPageUI.FIND_BY_DATE_BUTTON);
    }

    @Step("Find transactions by date range: {fromDate} to {toDate}")
    public void findByDateRange(String fromDate, String toDate) {
        sendKeyToElement(FindTransactionsPageUI.FIND_BY_DATE_RANGE_FROM_INPUT, fromDate);
        sendKeyToElement(FindTransactionsPageUI.FIND_BY_DATE_RANGE_TO_INPUT, toDate);
        clickToElement(FindTransactionsPageUI.FIND_BY_DATE_RANGE_BUTTON);
    }

    @Step("Find transactions by amount: {amount}")
    public void findByAmount(String amount) {
        sendKeyToElement(FindTransactionsPageUI.FIND_BY_AMOUNT_INPUT, amount);
        clickToElement(FindTransactionsPageUI.FIND_BY_AMOUNT_BUTTON);
    }

    public int getTransactionCount() {
        try {
            waitForAllElementsVisible(FindTransactionsPageUI.TRANSACTION_RESULTS);
            List<WebElement> rows = getElements(FindTransactionsPageUI.TRANSACTION_RESULTS);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isNoResultsDisplayed() {
        return isElementDisplayed(FindTransactionsPageUI.NO_RESULTS_MESSAGE);
    }

    public String getPageHeader() {
        waitForElementVisible(FindTransactionsPageUI.PAGE_TITLE);
        return getElementText(FindTransactionsPageUI.PAGE_TITLE);
    }
}
