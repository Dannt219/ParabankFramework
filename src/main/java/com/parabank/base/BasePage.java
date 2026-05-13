package com.parabank.base;

import com.parabank.utils.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WaitHelper waitHelper;
    protected final Logger logger;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.logger = LogManager.getLogger(this.getClass());
    }

    // ==================== Locator Resolution ====================

    private By getByLocator(String locator) {
        if (locator.startsWith("//") || locator.startsWith("(//")) {
            return By.xpath(locator);
        } else if (locator.startsWith("css=")) {
            return By.cssSelector(locator.substring(4));
        } else if (locator.startsWith("id=")) {
            return By.id(locator.substring(3));
        } else if (locator.startsWith("name=")) {
            return By.name(locator.substring(5));
        } else if (locator.startsWith("class=")) {
            return By.className(locator.substring(6));
        } else if (locator.startsWith("link=")) {
            return By.linkText(locator.substring(5));
        } else {
            return By.xpath(locator);
        }
    }

    private String formatLocator(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    // ==================== Element Retrieval ====================

    private WebElement getElement(String locator) {
        return driver.findElement(getByLocator(locator));
    }

    protected List<WebElement> getElements(String locator) {
        return driver.findElements(getByLocator(locator));
    }

    protected List<WebElement> getElements(String locator, String... values) {
        return driver.findElements(getByLocator(formatLocator(locator, values)));
    }

    // ==================== Wait Methods ====================

    protected void waitForElementVisible(String locator) {
        waitHelper.waitForVisibility(getByLocator(locator));
    }

    protected void waitForElementVisible(String locator, String... values) {
        waitHelper.waitForVisibility(getByLocator(formatLocator(locator, values)));
    }

    protected void waitForElementClickable(String locator) {
        waitHelper.waitForClickable(getByLocator(locator));
    }

    protected void waitForElementClickable(String locator, String... values) {
        waitHelper.waitForClickable(getByLocator(formatLocator(locator, values)));
    }

    protected void waitForAllElementsVisible(String locator) {
        waitHelper.waitForAllVisible(getByLocator(locator));
    }

    // ==================== Click Actions ====================

    protected void clickToElement(String locator) {
        waitForElementClickable(locator);
        getElement(locator).click();
    }

    protected void clickToElement(String locator, String... values) {
        String dynamicLocator = formatLocator(locator, values);
        waitForElementClickable(dynamicLocator);
        getElement(dynamicLocator).click();
    }

    // ==================== Input Actions ====================

    protected void sendKeyToElement(String locator, String value) {
        waitForElementVisible(locator);
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected void sendKeyToElement(String locator, String value, String... dynamicValues) {
        String dynamicLocator = formatLocator(locator, dynamicValues);
        waitForElementVisible(dynamicLocator);
        WebElement element = getElement(dynamicLocator);
        element.clear();
        element.sendKeys(value);
    }

    // ==================== Text Retrieval ====================

    protected String getElementText(String locator) {
        waitForElementVisible(locator);
        return getElement(locator).getText();
    }

    protected String getElementText(String locator, String... values) {
        String dynamicLocator = formatLocator(locator, values);
        waitForElementVisible(dynamicLocator);
        return getElement(dynamicLocator).getText();
    }

    // ==================== Display Check ====================

    protected boolean isElementDisplayed(String locator) {
        try {
            waitForElementVisible(locator);
            return getElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementDisplayed(String locator, String... values) {
        try {
            String dynamicLocator = formatLocator(locator, values);
            waitForElementVisible(dynamicLocator);
            return getElement(dynamicLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== Dropdown Actions ====================

    protected void selectItemInDropdownByText(String locator, String text) {
        waitForElementVisible(locator);
        waitHelper.waitForDropdownPopulated(getByLocator(locator));
        new Select(getElement(locator)).selectByVisibleText(text);
    }

    protected void selectItemInDropdownByValue(String locator, String value) {
        waitForElementVisible(locator);
        waitHelper.waitForDropdownPopulated(getByLocator(locator));
        new Select(getElement(locator)).selectByValue(value);
    }

    protected void selectItemInDropdownByIndex(String locator, int index) {
        waitForElementVisible(locator);
        waitHelper.waitForDropdownOptionCount(getByLocator(locator), index + 1);
        new Select(getElement(locator)).selectByIndex(index);
    }
}
