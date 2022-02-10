package com.sinensia.helloselenide.cucumber;

import com.sinensia.helloselenide.CartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.open;

public class RobobarStepDefinitions {

    CartPage cartPage;

    @Given("user opens robobar website")
    public void userOpensRobobarWebsite() {
        open("http://localhost:3000");
        cartPage = new CartPage();
    }

    @When("user adds a cola")
    public void userAddsACola() { cartPage.addCola(); }

    @And("user adds a cola")
    public void userAddsTwoColas() { cartPage.addCola(); }

    @Then("total should be €{double}")
    public void totalShouldBe(Double total) {
        cartPage.total().shouldBe(exactText("€"+String.format("%.2f", total)));
    }
}
