package com.taylor.gerenciador_despesas.respository;

import com.taylor.gerenciador_despesas.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
