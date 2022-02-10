package com.sinensia.helloselenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

// http://localhost:3000/#!/review
public class OrderPage {

    //Captura de elementos

    public SelenideElement alertMessage = $("p");

    public SelenideElement confirmationMessage = $("p");

    //Metodo de los elementos

    public SelenideElement getAlertMessage() {
        return alertMessage;
    }

    public SelenideElement getConfirmationMessage() {
        return confirmationMessage;
    }
}
