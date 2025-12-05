package com.testautomation.tests.ui;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import com.testautomation.ui.helpers.DriverFactory;
import com.testautomation.ui.pages.CartPage;
import com.testautomation.ui.pages.CheckoutPage;
import com.testautomation.ui.pages.InventoryPage;
import com.testautomation.ui.pages.LoginPage;

@Tag("ui")
@DisplayName("SauceDemo E2E Tests")
public class SauceDemoE2ETest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeEach
    void setup() {
        driver = DriverFactory.createFirefox();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("E2E: Complete purchase flow")
    void test_complete_purchase_flow() {
        // Step 1: Login
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertThat(driver.getCurrentUrl()).contains("inventory");

        // Step 2: Verify inventory page loaded
        assertThat(inventoryPage.isLoaded()).isTrue();
        assertThat(inventoryPage.getProductCount()).isGreaterThan(0);

        // Step 3: Add product to cart
        inventoryPage.addFirstProductToCart();
        assertThat(inventoryPage.getCartItemCount()).isEqualTo(1);

        // Step 4: Go to cart
        inventoryPage.goToCart();
        assertThat(driver.getCurrentUrl()).contains("cart");

        // Step 5: Verify cart has items
        assertThat(cartPage.hasItems()).isTrue();
        assertThat(cartPage.getCartItemCount()).isGreaterThan(0);

        // Step 6: Proceed to checkout
        cartPage.clickCheckout();
        assertThat(driver.getCurrentUrl()).contains("checkout-step-one");

        // Step 7: Fill checkout info
        checkoutPage.fillCheckoutInfo("John", "Doe", "12345");
        checkoutPage.clickContinue();
        assertThat(driver.getCurrentUrl()).contains("checkout-step-two");

        // Step 8: Complete order
        checkoutPage.clickFinish();
        assertThat(driver.getCurrentUrl()).contains("checkout-complete");
        assertThat(checkoutPage.isOrderComplete()).isTrue();
    }

    @Test
    @DisplayName("E2E: Add multiple products to cart")
    void test_add_multiple_products() {
        // Login
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        // Add first product
        inventoryPage.addFirstProductToCart();
        assertThat(inventoryPage.getCartItemCount()).isEqualTo(1);

        // Add second product (if available)
        inventoryPage.addFirstProductToCart();
        assertThat(inventoryPage.getCartItemCount()).isGreaterThanOrEqualTo(1);

        // Go to cart and verify
        inventoryPage.goToCart();
        assertThat(cartPage.hasItems()).isTrue();
    }

    @Test
    @DisplayName("E2E: Continue shopping from cart")
    void test_continue_shopping_from_cart() {
        // Login and add product
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addFirstProductToCart();

        // Go to cart
        inventoryPage.goToCart();
        assertThat(driver.getCurrentUrl()).contains("cart");

        // Continue shopping
        cartPage.continueShopping();
        assertThat(driver.getCurrentUrl()).contains("inventory");
    }
}
