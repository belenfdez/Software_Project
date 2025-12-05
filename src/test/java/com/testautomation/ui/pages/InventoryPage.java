package com.testautomation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {
    private static final By INVENTORY_CONTAINER = By.id("inventory_container");
    private static final By FIRST_ADD_TO_CART = By.cssSelector("button[id^='add-to-cart']");
    private static final By CART_ICON = By.id("shopping_cart_container");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return !driver.findElements(INVENTORY_CONTAINER).isEmpty();
    }

    public void addFirstProductToCart() {
        driver.findElement(FIRST_ADD_TO_CART).click();
    }

    public void goToCart() {
        driver.findElement(CART_ICON).click();
    }
}
