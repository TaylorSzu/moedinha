package com.taylor.gerenciador_despesas.dto.expense;

import java.math.BigDecimal;

public record UpdateExpenseDTO(
        Long id,
        String description,
        BigDecimal amount,
        String category
) {
}
