package com.sinensia.helloselenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class CartSuiteTest {

    CartPage cartPage = new CartPage();

    //Ajusta el tamaño del browser
    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    //Inicia la aplicacion
    @BeforeEach
    public void setUp() {
        open("http://localhost:3000/");
    }

    //Añade un producto y comprueba su valor
    @Test
    public void onexCola() {
        cartPage.addCola();
        cartPage.total().shouldBe(text("€1.25"));
    }

    //Añade un producto y comprueba su valor
    @Test
    public void onexBeer() {
        cartPage.addBeer();
        cartPage.total().shouldBe(text("€2.00"));
    }

    //Añade un producto y comprueba su valor
    @Test
    public void onexWine() {
        cartPage.addWine();
        cartPage.total().shouldBe(text("€3.00"));
    }

    //Añade dos productos y comprueba su valor
    @Test
    public void onexColaOnexBeer() {
        cartPage.addCola();
        cartPage.addBeer();
        cartPage.total().shouldBe(text("€3.25"));
    }

    //Añade tres productos y comprueba su valor
    @Test
    public void onexCola1xBeer1xWine() {
        cartPage.addCola();
        cartPage.addBeer();
        cartPage.addWine();
        cartPage.total().shouldBe(text("€6.25"));
    }

    //Añade dos productos y comprueba su valor
    @Test
    public void twoxWine() {
        cartPage.addWine();
        cartPage.addWine();
        cartPage.total().shouldBe(text("€6.00"));
    }

    //Añade dos productos y comprueba su valor
    @Test
    public void twoxBeer() {
        cartPage.addBeer();
        cartPage.addBeer();
        cartPage.total().shouldBe(text("€4.00"));
    }

    //Añade dos productos y comprueba su valor
    @Test
    public void twoxCola() {
        cartPage.addCola();
        cartPage.addCola();
        cartPage.total().shouldBe(text("€2.50"));
    }
}



