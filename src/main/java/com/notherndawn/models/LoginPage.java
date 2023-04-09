package com.notherndawn.models;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;
    private final Locator userNameInput;
    private final Locator passwordInput;
    private final Locator loginButton;

    public LoginPage(Page page) {
        this.page = page;
        this.userNameInput = page.locator("[id='user-name']");
        this.passwordInput = page.locator("[id='password']");
        this.loginButton = page.locator("[id='login-button']");
    }

    public void navigate() {
        page.navigate("");
    }

    public void login(String username, String password) {
        userNameInput.fill(username);
        passwordInput.fill(password);
        loginButton.click();
    }
}
