package com.testautomation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private static final By CART_ITEMS = By.cssSelector(".cart_item");
    private static final By CONTINUE_SHOPPING = By.id("continue-shopping");
    private static final By CHECKOUT_BUTTON = By.id("checkout");
    private static final By TOTAL_PRICE = By.cssSelector(".summary_total_label");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasItems() {
        return isElementPresent(CART_ITEMS);
    }

    public int getCartItemCount() {
        return driver.findElements(CART_ITEMS).size();
    }

    public void continueShopping() {
        clickElement(CONTINUE_SHOPPING);
    }

    public void clickCheckout() {
        clickElement(CHECKOUT_BUTTON);
    }

    public String getTotalPrice() {
        return getText(TOTAL_PRICE);
    }
}
