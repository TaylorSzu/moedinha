package com.taylor.gerenciador_despesas.respository.query;

import java.math.BigDecimal;

public interface TotalByMonth {
    String getDate();
    BigDecimal getAmount();
}
