package com.testautomation.ui.services;

import org.openqa.selenium.WebDriver;

import com.testautomation.ui.pages.InventoryPage;
import com.testautomation.ui.pages.LoginPage;

public class LoginService {
    private final WebDriver driver;
    private final LoginPage loginPage;
    private final InventoryPage inventoryPage;

    public LoginService(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.inventoryPage = new InventoryPage(driver);
    }

    public InventoryPage loginAsStandardUser() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        return inventoryPage;
    }
}
