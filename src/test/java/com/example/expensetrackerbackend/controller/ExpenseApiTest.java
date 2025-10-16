package com.example.expensetrackerbackend.controller;

import com.example.expensetrackerbackend.model.Expense;
import com.example.expensetrackerbackend.repository.ExpenseRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

// Starts the application on a random port for testing
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExpenseApiTest {

    // Gets the random port number the application started on
    @LocalServerPort
    private int port;

    @Autowired
    private ExpenseRepository expenseRepository;

    @BeforeEach
    void setUp() {
        // Set the base URI and port for all REST Assured tests
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        // Clean the database before each test
        expenseRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        // Clean the database after each test
        expenseRepository.deleteAll();
    }

    /**
     * API Test Case 1:
     * Tests fetching all expenses from a clean database.
     */
    @Test
    void shouldGetAllExpenses() {
        // Pre-load one expense to test with
        Expense expense = new Expense();
        expense.setDescription("Initial Test Expense");
        expense.setAmount(500.0);
        expenseRepository.save(expense);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/expenses")
                .then()
                .statusCode(200) // Validate response code
                .body("size()", equalTo(1)) // Validate payload (should be 1 item)
                .body("[0].description", equalTo("Initial Test Expense"));
    }

    /**
     * API Test Case 2:
     * Tests creating a new expense and validating the response.
     */
    @Test
    void shouldCreateNewExpense() {
        String requestBody = "{\"description\":\"New API Expense\", \"amount\":250.50}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/expenses")
                .then()
                .statusCode(200) // Validate response code
                .body("id", notNullValue()) // Validate payload (should have an ID)
                .body("description", equalTo("New API Expense")) // Validate payload
                .body("amount", equalTo(250.50f)); // JSON numbers are often floats
    }
}