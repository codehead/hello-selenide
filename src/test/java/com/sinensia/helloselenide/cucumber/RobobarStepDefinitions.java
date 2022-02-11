package com.sinensia.helloselenide.cucumber;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;

import com.sinensia.helloselenide.CartPage;
import com.sinensia.helloselenide.CheckoutPage;
import com.sinensia.helloselenide.OrderPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class RobobarStepDefinitions {

    private final CartPage cartPage = new CartPage();
    private CheckoutPage checkoutPage;
    private OrderPage orderPage;

    @Given("user opens robobar website")
    @Given("l'usuari entra al bar")
    public void openRobobar() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
        open("http://192.168.62.11:3000/");
        checkoutPage = null;
    }

    @When("user adds a cola")
    @When("l'usuari afegeix una cocacola")
    public void userAddsaCola() {
        cartPage.addCola();
    }

    @Then("total should be {string}")
    public void totalShoudBe(String total) {
        cartPage.total().shouldBe(exactText(total));
    }

    @Then("total should be €{double}")
    @Then("el total ha de ser {double}€")
    public void totalShouldBeDouble(Double total) {
        cartPage.total().shouldBe(exactText(String.format("€%.2f", total)));
    }

    @When("user adds a beer")
    public void userAddsABeer() {
        cartPage.addBeer();
    }

    @When("user adds a wine")
    public void userAddsAWine() {
        cartPage.addWine();
    }

    @When("user checks out")
    public void userChecksOut() {
        checkoutPage = cartPage.checkout();
    }

    @When("user is {int} years old")
    public void userIsYearsOld(int age) {
        checkoutPage.setAge(String.valueOf(age));
    }

    @Then("robobar does not allow checkout")
    public void robobarDoesNotAllowCheckout() {
        orderPage = checkoutPage.confirm();
        orderPage.alert().shouldNotBe(hidden);
    }

    @Then("robobar confirms order")
    public void robobarConfirmsOrder() {
        orderPage = checkoutPage.confirm();
        orderPage.alert().shouldBe(hidden);
        orderPage.confirmationMessage().shouldBe(matchText("Coming right up"));
    }

    @When("user adds a cola and a beer")
    public void userAddsAColaAndABeer() {
        cartPage.addCola();
        cartPage.addBeer();
    }

    @When("user adds {int} cola")
    public void userAddsCola(int n) {
        for(int i=0; i<n; ++i) {
            cartPage.addCola();
        }
    }

    @When("user adds {int} beers")
    public void userAddsNBeers(int n) {
        for(int i=0; i<n; ++i) {
            cartPage.addBeer();
        }
    }

    @When("user adds {int} wines")
    public void userAddsNWines(int n) {
        for(int i=0; i<n; ++i) {
            cartPage.addWine();
        }
    }

    @When("user adds {int} cola {int} beer {int} wine")
    public void userAddsColaBeerWine(int cola, int beer, int wine) {
        userAddsCola(cola);
        userAddsNBeers(beer);
        userAddsNWines(wine);
    }

    @Given("user is ready to check out with alcohol")
    public void userIsReadyToCheckOutWithAlcohol() {
        openRobobar();
        userAddsABeer();
        userChecksOut();
    }

    @But("checkout result is {string}")
    public void checkoutResultIsExpected(String expected) {
        throw new PendingException();
    }
}


