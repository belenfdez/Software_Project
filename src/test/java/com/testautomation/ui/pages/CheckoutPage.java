package com.testautomation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private static final By FIRST_NAME = By.id("first-name");
    private static final By LAST_NAME = By.id("last-name");
    private static final By POSTAL_CODE = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By FINISH_BUTTON = By.id("finish");
    private static final By ORDER_CONFIRMATION = By.cssSelector(".complete-header");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        sendKeys(FIRST_NAME, firstName);
        sendKeys(LAST_NAME, lastName);
        sendKeys(POSTAL_CODE, postalCode);
    }

    public void clickContinue() {
        clickElement(CONTINUE_BUTTON);
    }

    public void clickFinish() {
        clickElement(FINISH_BUTTON);
    }

    public boolean isOrderComplete() {
        return isElementPresent(ORDER_CONFIRMATION);
    }

    public String getConfirmationMessage() {
        return getText(ORDER_CONFIRMATION);
    }
}
