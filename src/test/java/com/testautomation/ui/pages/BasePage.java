package com.testautomation.ui.pages;

import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    private static final int TIMEOUT_SECONDS = 10;
    private static final Logger logger = Logger.getLogger(BasePage.class.getName());

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
    }

    protected void waitForUrlContains(String fragment) {
        logger.info("Waiting for URL to contain: " + fragment);
        wait.until(ExpectedConditions.urlContains(fragment));
    }

    protected WebElement waitForElement(By locator) {
        logger.info("Waiting for element: " + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementClickable(By locator) {
        logger.info("Waiting for element to be clickable: " + locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void clickElement(By locator) {
        logger.info("Clicking element: " + locator);
        waitForElementClickable(locator);
        driver.findElement(locator).click();
    }

    protected void sendKeys(By locator, String text) {
        logger.info("Sending keys to element: " + locator);
        waitForElement(locator);
        driver.findElement(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        logger.info("Getting text from element: " + locator);
        return waitForElement(locator).getText();
    }

    protected boolean isElementPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            logger.fine("Element not present: " + locator);
            return false;
        }
    }

    protected void takeScreenshot(String name) {
        // Screenshot capability - can be enhanced with Allure integration
        logger.info("Screenshot requested: " + name);
    }
}
