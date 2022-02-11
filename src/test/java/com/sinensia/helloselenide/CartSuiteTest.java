package com.sinensia.helloselenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class CartSuiteTest {
    CartPage cartPage = new CartPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @BeforeEach
    public void setUp() {
        open("http://192.168.62.11:3000");
    }

    @Test
    public void startTest() {
        cartPage.checkoutButton().shouldBe(disabled);
    }

    @Test
    public void colaTest() {
        cartPage.checkoutButton().shouldBe(disabled);
        cartPage.addCola();
        cartPage.total().shouldBe(text("€1.25"));
        cartPage.addCola();
        cartPage.total().shouldBe(text("€2.50"));
        cartPage.checkoutButton().shouldBe(enabled);
    }
}
