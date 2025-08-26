Feature: Buy products
    As a customer
    I want to buy products

Background:
    Given the store is ready to service customers
    And a product "Bread" with price 20.50 and stock of 5 exists
    And a product "Jam" with price 80.00 and stock of 10 exists
    And a product "Sugar" with price 10.00 and stock of 5 exists

Scenario: Buy one product
    When I buy "Bread" with quantity 2
    Then total should be 41.00

Scenario: Buy multiple products
    When I buy "Bread" with quantity 2
    And I buy "Jam" with quantity 1
    And I buy "Sugar" with quantity 1
    Then total should be 131.00

Scenario: Buy product with insufficient stock
    When I try to buy "Bread" with quantity 6
    Then I should get an error with message "Not enough stock"

Scenario: Buy product with exact stock amount
    When I buy "Sugar" with quantity 5
    Then total should be 50.00
    And product "Sugar" should have 0 items in stock