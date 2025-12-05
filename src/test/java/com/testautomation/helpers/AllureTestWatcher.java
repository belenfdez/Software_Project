package com.testautomation.helpers;

import java.io.ByteArrayInputStream;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;

public class AllureTestWatcher implements TestWatcher {
    private static final Logger logger = Logger.getLogger(AllureTestWatcher.class.getName());

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        logger.info("Test disabled: " + context.getDisplayName());
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.info("Test successful: " + context.getDisplayName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        logger.warning("Test aborted: " + context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.severe("Test failed: " + context.getDisplayName());
        logger.severe("Cause: " + cause.getMessage());
    }

    public static void captureScreenshot(WebDriver driver, String name) {
        try {
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(name, "image/png", new ByteArrayInputStream(screenshot), "png");
            }
        } catch (Exception e) {
            logger.warning("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
