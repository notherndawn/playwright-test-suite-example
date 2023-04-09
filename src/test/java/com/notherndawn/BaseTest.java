package com.notherndawn;

import com.microsoft.playwright.*;
import com.notherndawn.config.Configuration;
import com.notherndawn.config.ConfigurationManager;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    private Playwright playwright;
    private Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected static Configuration configuration;

    @BeforeAll
    void launchBrowser() {
        configuration = ConfigurationManager.getConfiguration();
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext(new Browser.NewContextOptions().setBaseURL(configuration.baseUrl()));
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }
}
