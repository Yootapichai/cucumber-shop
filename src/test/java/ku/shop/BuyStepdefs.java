package ku.shop;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;
    private String errorMessage;

    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();
    }

    @Given("a product {string} with price {float} and stock of {int} exists")
    public void a_product_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) throws InsufficientStockException {
        Product prod = catalog.getProduct(name);
        order.addItem(prod, quantity);
    }

    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }

    @When("I try to buy {string} with quantity {int}")
    public void i_try_to_buy_with_quantity(String name, int quantity) {
        try {
            Product prod = catalog.getProduct(name);
            order.addItem(prod, quantity);
        } catch (InsufficientStockException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("I should get an error with message {string}")
    public void i_should_get_an_error_with_message(String expectedMessage) {
        assertTrue(errorMessage != null && errorMessage.contains(expectedMessage),
                "Expected error containing: " + expectedMessage + " but got: " + errorMessage);
    }

    @And("product {string} should have {int} items in stock")
    public void product_should_have_items_in_stock(String name, int expectedStock) {
        Product prod = catalog.getProduct(name);
        assertEquals(expectedStock, prod.getStock());
    }

}

