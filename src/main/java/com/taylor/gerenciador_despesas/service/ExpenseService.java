package com.taylor.gerenciador_despesas.service;

import com.taylor.gerenciador_despesas.domain.Expense;
import com.taylor.gerenciador_despesas.domain.User;
import com.taylor.gerenciador_despesas.excption.NotFoundException;
import com.taylor.gerenciador_despesas.respository.ExpenseRepository;
import com.taylor.gerenciador_despesas.respository.query.TotalByCategory;
import com.taylor.gerenciador_despesas.respository.query.TotalByMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    public Expense create(Expense expense){
        expense.setDate(LocalDate.now());
        return expenseRepository.save(expense);
    }

    public void update(Expense expense){
        Expense foundExpense = findById(expense.getId());
        expense.setDate(foundExpense.getDate());
        expenseRepository.save(expense);
    }

    public Expense findById(Long id){
        return expenseRepository.findById(id).orElseThrow(() -> new NotFoundException("Expense not found"));
    }

    public BigDecimal totalSent(Long id) {
        return expenseRepository.sumByUser(id);
    }

    public List<TotalByCategory> totalByCategory(Long userId){
        return expenseRepository.totalByCategory(userId);
    }

    public List<TotalByMonth> totalByMonth(Long userId) {
        return expenseRepository.sumByMonth(userId);
    }

    public Expense biggestExpense(Long id){
        User foundUser = userService.findById(id);
        return expenseRepository.findTopByUserOrderByAmountDesc(foundUser);
    }

    public void delete(Long id){
        findById(id);
        expenseRepository.deleteById(id);
    }
}
