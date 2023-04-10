package com.notherndawn.tests;

import com.notherndawn.BaseTest;
import com.notherndawn.models.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPageTest extends BaseTest {

    @Test
    @DisplayName("Successful login test")
    void successfulLoginTest() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        loginPage.login(configuration.username(), configuration.password());

        assertThat(page).hasURL("inventory.html");
    }
}
