package com.taylor.gerenciador_despesas.mapper;

import com.taylor.gerenciador_despesas.domain.Expense;
import com.taylor.gerenciador_despesas.dto.expense.*;
import com.taylor.gerenciador_despesas.respository.query.TotalByCategory;
import com.taylor.gerenciador_despesas.respository.query.TotalByMonth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ExpenseMapper {
    Expense toExpense(CreateExpenseDTO dto);
    Expense toExpense(ExpenseTotalDTO dto);
    Expense toExpense(UpdateExpenseDTO dto);
    ExpenseTotalDTO toExpenseTotalDTO(Expense expense);
    CreateExpenseDTO toCreateExpenseDto(Expense expense);
    TotalByCategoryDTO toTotalByCategoryDTO(TotalByCategory totalByCategory);
    List<TotalByCategoryDTO> totalByCategoryDTOS(List<TotalByCategory> totalByCategories);
    TotalByMonthDTO toTotalByMonthDTO(TotalByMonth totalByMonth);
    List<TotalByMonthDTO> toTotalByMonthsDTO(List<TotalByMonth> totalByMonths);
    BiggerExpenseDTO toBiggerExpenseDTO(Expense expense);
}
