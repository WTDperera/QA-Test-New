// src/main/java/com/example/expensetrackerbackend/controller/ExpenseController.java
package com.example.expensetrackerbackend.controller;

import com.example.expensetrackerbackend.model.Expense;
import com.example.expensetrackerbackend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity; // මේක අලුතෙන් import කරගන්න
import com.example.expensetrackerbackend.service.ExpenseService; // import service

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin("http://localhost:3000")

public class ExpenseController {
    @Autowired
    private ExpenseService expenseService; // Repository එක වෙනුවට Service එක inject කරනවා

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.addExpense(expense);
    }
}