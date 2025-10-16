Feature: Add Expense to the Tracker
  As a user of the expense tracker
  I want to be able to add new expenses
  So that I can keep a record of my spending

  Scenario: User adds a valid expense via the API
    Given the expense tracker application is running
    When I send a POST request to "/api/expenses" with description "Groceries" and amount 150.75
    Then the response status should be 200
    And the response body should contain the description "Groceries" and amount 150.75