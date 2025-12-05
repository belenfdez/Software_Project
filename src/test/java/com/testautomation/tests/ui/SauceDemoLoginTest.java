package com.testautomation.tests.ui;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.testautomation.ui.helpers.DriverFactory;

@Tag("ui")
public class SauceDemoLoginTest {
    private WebDriver driver;

    @BeforeEach
    void setup() {
        driver = DriverFactory.createFirefox();
    }

    @Test
    void test_login_successful() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        
        // Wait a moment for page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertThat(driver.getCurrentUrl()).contains("inventory");
    }

    @Test
    void test_login_with_invalid_credentials() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("invalid_user");
        driver.findElement(By.id("password")).sendKeys("wrong_pass");
        driver.findElement(By.id("login-button")).click();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Should show error message
        assertThat(driver.findElements(By.cssSelector("[data-test='error']"))).isNotEmpty();
    }
}
