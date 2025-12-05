package com.testautomation.tests.ui;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import com.testautomation.ui.helpers.DriverFactory;
import com.testautomation.ui.pages.LoginPage;

@Tag("ui")
@DisplayName("SauceDemo Login Tests")
public class SauceDemoLoginUITest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    void setup() {
        driver = DriverFactory.createFirefox();
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Login with valid credentials")
    void test_login_successful() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        
        assertThat(driver.getCurrentUrl()).contains("inventory");
    }

    @Test
    @DisplayName("Login with invalid username")
    void test_login_invalid_username() {
        loginPage.open();
        loginPage.login("invalid_user", "secret_sauce");
        
        assertThat(loginPage.hasErrorMessage()).isTrue();
        assertThat(loginPage.getErrorMessage()).containsIgnoringCase("username");
    }

    @Test
    @DisplayName("Login with invalid password")
    void test_login_invalid_password() {
        loginPage.open();
        loginPage.login("standard_user", "wrong_password");
        
        assertThat(loginPage.hasErrorMessage()).isTrue();
        assertThat(loginPage.getErrorMessage()).containsIgnoringCase("password");
    }

    @Test
    @DisplayName("Login with empty credentials")
    void test_login_empty_credentials() {
        loginPage.open();
        loginPage.login("", "");
        
        assertThat(loginPage.hasErrorMessage()).isTrue();
    }
}
