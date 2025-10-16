package com.example.expensetrackerbackend.bdd;

import com.example.expensetrackerbackend.model.Expense;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExpenseStepDefinitions {

    // TestRestTemplate is a convenient tool for making real HTTP requests in a test environment
    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<Expense> response;

    @Given("the expense tracker application is running")
    public void the_expense_tracker_application_is_running() {
        // This step is implicitly handled by the @SpringBootTest annotation in CucumberSpringConfiguration.
        // We don't need to write any code here. It's here to make the Gherkin scenario more readable.
    }

    @When("I send a POST request to {string} with description {string} and amount {double}")
    public void i_send_a_post_request_to_with_description_and_amount(String path, String description, Double amount) {
        Expense newExpense = new Expense();
        newExpense.setDescription(description);
        newExpense.setAmount(amount);

        // We make a real POST request to our running application
        response = restTemplate.postForEntity(path, newExpense, Expense.class);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer expectedStatus) {
        assertEquals(expectedStatus, response.getStatusCode().value());
    }

    @Then("the response body should contain the description {string} and amount {double}")
    public void the_response_body_should_contain_the_description_and_amount(String expectedDescription, Double expectedAmount) {
        Expense savedExpense = response.getBody();
        assertNotNull(savedExpense, "Response body should not be null");
        assertNotNull(savedExpense.getId(), "Saved expense should have an ID");
        assertEquals(expectedDescription, savedExpense.getDescription());
        assertEquals(expectedAmount, savedExpense.getAmount());
    }
}