package com.sinensia.helloselenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class AgeSuiteTest {

    CartPage cartPage = new CartPage();

    //Configuracion de tests

    @BeforeAll
    public static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        open("/");
    }

    //Tests

    @Test
    public void onexAdultBeerCheckout() {

        cartPage.btnCheckout.shouldBe(disabled);
        cartPage.addBeer();
        cartPage.btnCheckout.shouldBe(enabled);

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.btnOrder.shouldBe(disabled);
        checkoutPage.ageInput.shouldBe(visible);
        checkoutPage.getAgeInput();
        checkoutPage.sendKeysAge("21");
        checkoutPage.btnOrder.shouldBe(enabled);

        OrderPage orderPage = checkoutPage.getOrder();
        orderPage.getConfirmationMessage().shouldBe(text("Coming right up! ~bzzzt~"));
    }

    @Test
    public void onexUnderageBeerCheckout() {
        cartPage.btnCheckout.shouldBe(disabled);
        cartPage.addBeer();
        cartPage.btnCheckout.shouldBe(enabled);

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.btnOrder.shouldBe(disabled);
        checkoutPage.ageInput.shouldBe(visible);
        checkoutPage.getAgeInput();
        checkoutPage.sendKeysAge("17");
        checkoutPage.btnOrder.shouldBe(enabled);

        OrderPage orderPage = checkoutPage.getOrder();
        orderPage.getAlertMessage().shouldBe(text("Only adults can buy alcohol!"));
    }

    @Test
    public void onexColaCheckout() {
        cartPage.btnCheckout.shouldBe(disabled);
        cartPage.addCola();
        cartPage.btnCheckout.shouldBe(enabled);

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.ageInput.shouldBe(hidden);
        checkoutPage.btnCancel.shouldBe(enabled);
        checkoutPage.btnOrder.shouldBe(enabled);

        OrderPage orderPage = checkoutPage.getOrder();
        orderPage.getConfirmationMessage().shouldBe(text("Coming right up! ~bzzzt~"));
    }

    @Test
    public void cancelOrder() {
        cartPage.btnCheckout.shouldBe(disabled);
        cartPage.addCola();
        cartPage.btnCheckout.shouldBe(enabled);

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.ageInput.shouldBe(hidden);
        checkoutPage.btnCancel.shouldBe(enabled);
        checkoutPage.btnOrder.shouldBe(enabled);

        cartPage = checkoutPage.getCancel();
        cartPage.total().shouldBe(text("€0.00"));
    }
}