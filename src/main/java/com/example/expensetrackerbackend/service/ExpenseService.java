package com.example.expensetrackerbackend.service;

import com.example.expensetrackerbackend.model.Expense;
import com.example.expensetrackerbackend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense addExpense(Expense expense) {
        if (expense.getAmount() == null || expense.getAmount() <= 0) {
            // අපි custom exception එකක් throw කරමු, හැබැයි ඒක තාම හදලා නෑ
            throw new IllegalArgumentException("Expense amount must be positive.");
        }
        if (expense.getDescription() == null || expense.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Expense description cannot be empty.");
        }
        return expenseRepository.save(expense);
    }
}