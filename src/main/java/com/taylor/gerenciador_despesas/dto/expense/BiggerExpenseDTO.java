package com.taylor.gerenciador_despesas.dto.expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BiggerExpenseDTO(
        String description,
        BigDecimal amount,
        String category,
        LocalDate date
) {
}
