package com.taylor.gerenciador_despesas.dto.expense;

import java.math.BigDecimal;

public record TotalByMonthDTO(
        String date,
        BigDecimal amount
) {
}
