// src/main/java/com/example/expensetrackerbackend/repository/ExpenseRepository.java
package com.example.expensetrackerbackend.repository;

import com.example.expensetrackerbackend.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}