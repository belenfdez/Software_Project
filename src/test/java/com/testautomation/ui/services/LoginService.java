package com.testautomation.ui.services;

import org.openqa.selenium.WebDriver;

import com.testautomation.ui.pages.CartPage;
import com.testautomation.ui.pages.CheckoutPage;
import com.testautomation.ui.pages.InventoryPage;
import com.testautomation.ui.pages.LoginPage;

public class LoginService {
    private final WebDriver driver;
    private final LoginPage loginPage;
    private final InventoryPage inventoryPage;
    private final CartPage cartPage;
    private final CheckoutPage checkoutPage;

    public LoginService(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.inventoryPage = new InventoryPage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
    }

    public InventoryPage loginAsStandardUser() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return inventoryPage;
    }

    public CartPage addProductAndGoToCart() {
        inventoryPage.addFirstProductToCart();
        inventoryPage.goToCart();
        return cartPage;
    }

    public void completeCheckout(String firstName, String lastName, String postalCode) {
        cartPage.clickCheckout();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        checkoutPage.fillCheckoutInfo(firstName, lastName, postalCode);
        checkoutPage.clickContinue();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        checkoutPage.clickFinish();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public LoginPage logout() {
        // Click menu and logout if available
        return loginPage;
    }
}
