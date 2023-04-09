package com.notherndawn.tests;

import com.notherndawn.BaseTest;
import com.notherndawn.models.LoginPage;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPageTest extends BaseTest {

    @Test
    void successLoginTest() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        loginPage.login("standard_user", "secret_sauce");

        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
    }
}
