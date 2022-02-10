package com.sinensia.helloselenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

// http://localhost:3000/#!/review
public class CheckoutPage {

    //Captura de los selectores

    //Ventana de introducir edad
    public SelenideElement ageInput = $("#ageInput" );

    //Boton de order
    public SelenideElement btnOrder = $(".btn-success");

    //Boton de cancelar
    public SelenideElement btnCancel = $(".btn-default");

    //Acciones concretas en los selectores

    //Clica sobre el ageInput
    public void getAgeInput() {
        ageInput.click();
    }

    //Escribe una edad
    public void sendKeysAge(String age) {
        ageInput.sendKeys(age);
    }

    //Clica en cancel
    public CartPage getCancel() {
        btnCancel.click();
        return page(CartPage.class);
    }

    //Clica en order
    public OrderPage getOrder() {
        btnOrder.click();
        return page(OrderPage.class);
    }
}