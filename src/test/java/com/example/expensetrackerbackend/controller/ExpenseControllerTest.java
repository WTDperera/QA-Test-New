package com.example.expensetrackerbackend.controller;

import com.example.expensetrackerbackend.model.Expense;
import com.example.expensetrackerbackend.service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenseController.class)
public class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseService expenseService; // Repository වෙනුවට Service එක mock කරනවා

    @Test
    void whenGetAllExpenses_thenReturnExpensesList() throws Exception {
        // 1. Arrange: Test එකට අවශ්‍ය දත්ත සහ බොරු හැසිරීම් (mock behavior) සකස් කිරීම
        Expense expense1 = new Expense();
        expense1.setId(1L);
        expense1.setDescription("Test Expense 1");
        expense1.setAmount(100.0);

        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(expense1);

        // Mockito වලට කියනවා: කවුරුහරි expenseService.getAllExpenses() call කළොත්,
        // අපි හදපු expenseList එක return කරන්න කියලා
        when(expenseService.getAllExpenses()).thenReturn(expenseList);

        // 2. Act & 3. Assert: Request එක යවලා, එන ප්‍රතිඵලය බලාපොරොත්තු වෙන දේමද කියලා තහවුරු කිරීම
        mockMvc.perform(get("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value("Test Expense 1"));
    }

    @Test
    void whenAddExpense_withInvalidAmount_thenThrowError() throws Exception {
        // 1. Arrange
        String invalidExpenseJson = "{\"description\":\"Invalid Expense\", \"amount\":-100.0}";

        // අපි service layer එකෙන් exception එකක් එන විදිහට mock කරනවා
        // any(Expense.class) කියන්නේ Expense class එකේ ඕනෑම object එකක් ආවොත් කියන එකයි
        when(expenseService.addExpense(any(Expense.class)))
                .thenThrow(new IllegalArgumentException("Expense amount must be positive."));

        // 2. Act & 3. Assert
        mockMvc.perform(post("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidExpenseJson))
                .andExpect(status().isBadRequest()); // අපේ GlobalExceptionHandler එක මේ exception එක අල්ලගෙන 400 Bad Request එවන්න ඕනේ
    }
}