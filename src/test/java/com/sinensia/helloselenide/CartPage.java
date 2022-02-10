package com.sinensia.helloselenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

// http://localhost:3000/#!/
public class CartPage {

    //Captura de los selectores

    //Boton + de añadir cola
    public SelenideElement btnAddCola = $("html > body > robo-robobar > div > div:nth-of-type(2) > robo-place-order > div:nth-of-type(2) > table > tbody > tr:nth-of-type(1) > td:nth-of-type(3) > div > div > div:nth-of-type(2) > button");

    //Boton + de añadir beer
    public SelenideElement btnAddBeer = $("html > body > robo-robobar > div > div:nth-of-type(2) > robo-place-order > div:nth-of-type(2) > table > tbody > tr:nth-of-type(2) > td:nth-of-type(3) > div > div > div:nth-of-type(2) > button");

    //Boton + de añadir wine
    public SelenideElement btnAddWine = $("html > body > robo-robobar > div > div:nth-of-type(2) > robo-place-order > div:nth-of-type(2) > table > tbody > tr:nth-of-type(3) > td:nth-of-type(3) > div > div > div:nth-of-type(2) > button");

    //String de total
    public SelenideElement textTotal = $("th[class='ng-binding']");

    //Boton de submit
    public SelenideElement btnCheckout = $(".btn-success");

    //Acciones concretas en los selectores

    //Añade cola una vez
    public void addCola() { btnAddCola.click(); }

    //Añade cerveza una vez
    public void addBeer() { btnAddBeer.click(); }

    //Añade vino una vez
    public void addWine() { btnAddWine.click(); }

    //Captura el total
    public SelenideElement total() { return textTotal; }

    //Clica en submit y comprueba el cambio de pagina
    public CheckoutPage checkout() {
        btnCheckout.click();
        return page(CheckoutPage.class);
    }
}
