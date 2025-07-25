package com.taylor.gerenciador_despesas.mapper;

import com.taylor.gerenciador_despesas.annotation.EncoderMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderMapping {
    private final PasswordEncoder passwordEncoder;

    @EncoderMapping
    public String encode(String password){
        return password==null? null : passwordEncoder.encode(password);
    }
}

