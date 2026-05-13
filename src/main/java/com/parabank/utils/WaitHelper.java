package com.parabank.utils;

import com.parabank.constants.FrameworkConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitHelper {

    private static final Logger logger = LogManager.getLogger(WaitHelper.class);

    private final WebDriverWait wait;

    public WaitHelper(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT_TIMEOUT));
    }

    public WebElement waitForVisibility(By locator) {
        logger.debug("Waiting for element to be visible: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        logger.debug("Waiting for element to be clickable: {}", locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public List<WebElement> waitForAllVisible(By locator) {
        logger.debug("Waiting for all elements to be visible: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForPageLoad() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    public void waitForDropdownPopulated(By locator) {
        logger.debug("Waiting for dropdown to be populated: {}", locator);
        wait.until(d -> !new Select(d.findElement(locator)).getOptions().isEmpty());
    }

    public void waitForDropdownOptionCount(By locator, int minOptions) {
        logger.debug("Waiting for dropdown to have at least {} options: {}", minOptions, locator);
        wait.until(d -> new Select(d.findElement(locator)).getOptions().size() >= minOptions);
    }

    public void waitForAjax() {
        wait.until(d -> {
            Object active = ((JavascriptExecutor) d)
                    .executeScript("return (typeof jQuery === 'undefined') ? 0 : jQuery.active");
            return active instanceof Number n && n.intValue() == 0;
        });
    }
}
