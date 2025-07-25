package com.taylor.gerenciador_despesas.respository.query;

import java.math.BigDecimal;

public interface TotalByCategory {
    String getCategory();
    BigDecimal getAmount();
}
