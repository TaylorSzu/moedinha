package com.taylor.gerenciador_despesas.controller;

import com.taylor.gerenciador_despesas.dto.user.LoginDTO;
import com.taylor.gerenciador_despesas.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));
        String jwtToken = jwtService.generateToken((UserDetails) auth.getPrincipal()); //gera o token e me devolve esse token fazendo um conversão
        return ResponseEntity.ok(jwtToken);
    }

    //slc seria mais facil se eu so passe o user datail e claims personalizaveis
}
