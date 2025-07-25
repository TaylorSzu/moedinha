package com.taylor.gerenciador_despesas.dto.user;

public record UpdateUserDTO(
        Long id,
        String name,
        String email,
        String password
) {
}
