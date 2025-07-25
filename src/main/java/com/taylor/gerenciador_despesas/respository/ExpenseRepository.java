package com.taylor.gerenciador_despesas.respository;

import com.taylor.gerenciador_despesas.domain.Expense;
import com.taylor.gerenciador_despesas.domain.User;
import com.taylor.gerenciador_despesas.respository.query.TotalByCategory;
import com.taylor.gerenciador_despesas.respository.query.TotalByMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user.id = :userId")
    BigDecimal sumByUser(@Param("userId") Long userId);
    @Query("SELECT e.category AS category, SUM(e.amount) AS amount FROM Expense e WHERE e.user.id = :userId GROUP BY e.category")
    List<TotalByCategory> totalByCategory(@Param("userId") Long userId);
    @Query("SELECT FUNCTION('DATE_FORMAT', e.date, '%Y-%m') AS date, SUM(e.amount) AS amount FROM Expense e WHERE e.user.id = :userId GROUP BY FUNCTION('DATE_FORMAT', e.date, '%Y-%m')")
    List<TotalByMonth> sumByMonth(@Param("userId") Long userId);
    Expense findTopByUserOrderByAmountDesc(User user);
}
