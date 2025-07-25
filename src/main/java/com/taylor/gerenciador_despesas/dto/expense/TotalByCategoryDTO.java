package com.taylor.gerenciador_despesas.dto.expense;

import java.math.BigDecimal;

public record TotalByCategoryDTO(
        String category,
        BigDecimal amount
) {
}
