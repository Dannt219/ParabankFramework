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

    private String getDynamicLocator(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    // ==================== Element Retrieval ====================

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public WebElement getElement(WebDriver driver, String locator, String... values) {
        return driver.findElement(getByLocator(getDynamicLocator(locator, values)));
    }

    public List<WebElement> getElements(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    public List<WebElement> getElements(WebDriver driver, String locator, String... values) {
        return driver.findElements(getByLocator(getDynamicLocator(locator, values)));
    }

    // ==================== Wait Methods ====================

    public void waitForElementVisible(WebDriver driver, String locator) {
        waitHelper.waitForVisibility(getByLocator(locator));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... values) {
        waitHelper.waitForVisibility(getByLocator(getDynamicLocator(locator, values)));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        waitHelper.waitForClickable(getByLocator(locator));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... values) {
        waitHelper.waitForClickable(getByLocator(getDynamicLocator(locator, values)));
    }

    public void waitForElementPresence(WebDriver driver, String locator) {
        waitHelper.waitForPresence(getByLocator(locator));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        waitHelper.waitForInvisibility(getByLocator(locator));
    }

    public void waitForAllElementsVisible(WebDriver driver, String locator) {
        waitHelper.waitForAllVisible(getByLocator(locator));
    }

    // ==================== Click Actions ====================

    public void clickToElement(WebDriver driver, String locator) {
        waitForElementClickable(driver, locator);
        getElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... values) {
        String dynamicLocator = getDynamicLocator(locator, values);
        waitForElementClickable(driver, dynamicLocator);
        getElement(driver, dynamicLocator).click();
    }

    // ==================== Input Actions ====================

    public void sendKeyToElement(WebDriver driver, String locator, String value) {
        waitForElementVisible(driver, locator);
        WebElement element = getElement(driver, locator);
        element.clear();
        element.sendKeys(value);
    }

    public void sendKeyToElement(WebDriver driver, String locator, String value, String... dynamicValues) {
        String dynamicLocator = getDynamicLocator(locator, dynamicValues);
        waitForElementVisible(driver, dynamicLocator);
        WebElement element = getElement(driver, dynamicLocator);
        element.clear();
        element.sendKeys(value);
    }

    public void clearElement(WebDriver driver, String locator) {
        waitForElementVisible(driver, locator);
        getElement(driver, locator).clear();
    }

    // ==================== Text Retrieval ====================

    public String getElementText(WebDriver driver, String locator) {
        waitForElementVisible(driver, locator);
        return getElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... values) {
        String dynamicLocator = getDynamicLocator(locator, values);
        waitForElementVisible(driver, dynamicLocator);
        return getElement(driver, dynamicLocator).getText();
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        waitForElementVisible(driver, locator);
        return getElement(driver, locator).getAttribute(attributeName);
    }

    // ==================== Display Check ====================

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        try {
            waitForElementVisible(driver, locator);
            return getElement(driver, locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
        try {
            String dynamicLocator = getDynamicLocator(locator, values);
            waitForElementVisible(driver, dynamicLocator);
            return getElement(driver, dynamicLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== Dropdown Actions ====================

    public void selectItemInDropdownByText(WebDriver driver, String locator, String text) {
        waitForElementVisible(driver, locator);
        new Select(getElement(driver, locator)).selectByVisibleText(text);
    }

    public void selectItemInDropdownByValue(WebDriver driver, String locator, String value) {
        waitForElementVisible(driver, locator);
        new Select(getElement(driver, locator)).selectByValue(value);
    }

    public void selectItemInDropdownByIndex(WebDriver driver, String locator, int index) {
        waitForElementVisible(driver, locator);
        new Select(getElement(driver, locator)).selectByIndex(index);
    }

    // ==================== Element Count ====================

    public int getElementCount(WebDriver driver, String locator) {
        return getElements(driver, locator).size();
    }

    // ==================== Navigation ====================

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public void navigateToUrl(WebDriver driver, String url) {
        logger.info("Navigating to: {}", url);
        driver.get(url);
    }
}
