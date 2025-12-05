package com.testautomation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {
    private static final By INVENTORY_CONTAINER = By.id("inventory_container");
    private static final By INVENTORY_ITEMS = By.cssSelector(".inventory_item");
    private static final By FIRST_ADD_TO_CART = By.xpath("(//button[contains(@id, 'add-to-cart')])[1]");
    private static final By CART_ICON = By.id("shopping_cart_container");
    private static final By CART_BADGE = By.cssSelector(".shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isElementPresent(INVENTORY_CONTAINER);
    }

    public int getProductCount() {
        return driver.findElements(INVENTORY_ITEMS).size();
    }

    public void addFirstProductToCart() {
        clickElement(FIRST_ADD_TO_CART);
    }

    public void goToCart() {
        clickElement(CART_ICON);
    }

    public int getCartItemCount() {
        try {
            String badge = getText(CART_BADGE);
            return Integer.parseInt(badge);
        } catch (Exception e) {
            return 0;
        }
    }
}
