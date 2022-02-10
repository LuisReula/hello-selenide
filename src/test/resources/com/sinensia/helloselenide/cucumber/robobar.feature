Feature: Robobar cart
  Scenario: user adds a cola
    Given user opens robobar website
    When user adds a cola
    Then  total should be €1.25


  Scenario: user add two cola
    Given user opens robobar website
    When user adds a cola
    And user adds a cola
    Then  total should be €2.5
