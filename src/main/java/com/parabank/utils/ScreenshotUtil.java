package com.parabank.utils;

import com.parabank.constants.FrameworkConstants;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtil.class);

    private ScreenshotUtil() {
        // Prevent instantiation
    }

    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = testName + "_" + timestamp + ".png";

            Path screenshotDir = Paths.get(FrameworkConstants.SCREENSHOTS_PATH);
            Files.createDirectories(screenshotDir);

            Path screenshotPath = screenshotDir.resolve(fileName);
            Files.write(screenshotPath, screenshotBytes);

            // Attach to Allure report
            Allure.addAttachment(testName, new ByteArrayInputStream(screenshotBytes));

            logger.info("Screenshot captured: {}", screenshotPath);
            return screenshotPath.toString();
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
            return null;
        }
    }

}
