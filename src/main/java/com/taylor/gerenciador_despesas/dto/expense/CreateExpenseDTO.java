package com.taylor.gerenciador_despesas.dto.expense;

import java.math.BigDecimal;

public record CreateExpenseDTO(
        String description,
        BigDecimal amount,
        String category
) {
}
