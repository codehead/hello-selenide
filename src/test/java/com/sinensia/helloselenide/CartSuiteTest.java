package com.sinensia.helloselenide;

import com.automation.remarks.video.annotations.Video;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

public class CartSuiteTest {
    CartPage cartPage = new CartPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
    }

    @BeforeEach
    public void setUp() {
        open("/");
    }

    @Test
    public void startTest() {
        cartPage.checkoutButton().shouldBe(disabled);
    }

    @Test
    @TmsLink("oneColaTest")
    public void colaTest() {
        cartPage.checkoutButton().shouldBe(disabled);
        cartPage.addCola();
        cartPage.total().shouldBe(text("€1.25"));
    }

    @Test
    @Video(name = "twoColaVideo")
    @Link("https://example.org")
    public void twoColaTest() {
        cartPage.checkoutButton().shouldBe(disabled);
        cartPage.addCola();
        cartPage.total().shouldBe(text("€1.25"));
        cartPage.addCola();
        cartPage.total().shouldBe(text("€2.50"));
        cartPage.checkoutButton().shouldBe(enabled);
        screenshot("twoColaTest");
    }
}
