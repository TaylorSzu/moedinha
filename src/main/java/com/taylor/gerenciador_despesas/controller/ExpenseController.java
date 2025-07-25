package com.taylor.gerenciador_despesas.controller;

import com.taylor.gerenciador_despesas.domain.Expense;
import com.taylor.gerenciador_despesas.dto.expense.*;
import com.taylor.gerenciador_despesas.mapper.ExpenseMapper;
import com.taylor.gerenciador_despesas.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ExpenseMapper expenseMapper;

    @PostMapping
    public ResponseEntity<CreateExpenseDTO> register(@RequestBody CreateExpenseDTO dto) {
        System.out.println("DTO recebido: " + dto);
        Expense expense = expenseMapper.toExpense(dto);
        System.out.println("Expense mapeado: " + expense);
        CreateExpenseDTO resposne = expenseMapper.toCreateExpenseDto(expenseService.create(expense));
        return ResponseEntity.created(null).body(resposne);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateExpenseDTO dto) {
        expenseService.update(expenseMapper.toExpense(dto));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total/{id}") //arrumar dps
    public ResponseEntity<ExpenseTotalDTO> totalSent(@PathVariable Long id){
        ExpenseTotalDTO response = new ExpenseTotalDTO(expenseService.totalSent(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/total/category/{id}")
    public ResponseEntity<List<TotalByCategoryDTO>> totalByCateory(@PathVariable Long id) {
        List<TotalByCategoryDTO> response = expenseMapper.totalByCategoryDTOS(expenseService.totalByCategory(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/total/month/{id}")
    public ResponseEntity<List<TotalByMonthDTO>> totalByMonth(@PathVariable Long id) {
        List<TotalByMonthDTO> response = expenseMapper.toTotalByMonthsDTO(expenseService.totalByMonth(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bigger/expense/{id}")
    public ResponseEntity<BiggerExpenseDTO> biggerExpense(@PathVariable Long id){
        BiggerExpenseDTO response = expenseMapper.toBiggerExpenseDTO(expenseService.biggestExpense(id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
