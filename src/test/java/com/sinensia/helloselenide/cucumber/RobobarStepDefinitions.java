package com.sinensia.helloselenide.cucumber;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;

import com.sinensia.helloselenide.CartPage;
import com.sinensia.helloselenide.CheckoutPage;
import com.sinensia.helloselenide.OrderPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class RobobarStepDefinitions {

    private final CartPage cartPage = new CartPage();
    private CheckoutPage checkoutPage;

    @Given("user opens robobar website")
    public void openRobobar() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        open("/");
        checkoutPage = null;
    }

    @When("user adds a cola")
    public void userAddsaCola() {
        cartPage.addCola();
    }

    @Then("total should be {string}")
    public void totalShoudBe(String total) {
        cartPage.total().shouldBe(exactText(total));
    }

    @Then("total should be €{double}")
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
        checkoutPage.sendKeysAge(String.valueOf(age));
    }

    @Then("robobar does not allow checkout")
    public void robobarDoesNotAllowCheckout() {
        OrderPage orderPage = new OrderPage();
        orderPage.getConfirmationMessage();
        orderPage.getAlertMessage().shouldNotBe(hidden);
    }

    @Then("robobar confirms order")
    public void robobarConfirmsOrder() {
        OrderPage orderPage = new OrderPage();
        orderPage.getAlertMessage().shouldBe(hidden);
        //orderPage.getConfirmationMessage().shouldBe(text("Coming right up! ~bzzzt~"));
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
}
