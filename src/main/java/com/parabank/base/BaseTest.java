package com.parabank.base;

import com.parabank.utils.ConfigReader;
import com.parabank.utils.DriverFactory;
import com.parabank.utils.ScreenshotUtil;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("") String browser) {
        String targetBrowser = browser.isEmpty() ? ConfigReader.getBrowser() : browser;
        logger.info("Setting up test with browser: {}", targetBrowser);
        DriverFactory.initDriver(targetBrowser);
        getDriver().get(ConfigReader.getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("Test FAILED: {}", result.getName());
            ScreenshotUtil.captureScreenshot(getDriver(), result.getName());
            Allure.step("Test failed - screenshot captured");
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.warn("Test SKIPPED: {}", result.getName());
        } else {
            logger.info("Test PASSED: {}", result.getName());
        }
        DriverFactory.quitDriver();
    }
}
