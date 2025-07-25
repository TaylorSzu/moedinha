package com.taylor.gerenciador_despesas.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) //"usuario" dono do token
                .claim("role", userDetails.getAuthorities()) //dados personalizados
                .setIssuedAt(new Date()) //quando ele foi criado
                .setExpiration(new Date(System.currentTimeMillis() + Duration.ofDays(1).toMillis())) //Tempo de expiração de 1 dia
                .signWith(getSignKey(), SignatureAlgorithm.HS256) //algoritimo de assinatura
                .compact(); //gera o token como um String
    }

    public String extractUsername(String token){ //extari o subejct do token ou quem é o dono
        return extractClaims(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){ //valida se o token é valido
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){ // verifica se o token expirou
        return extractExpiration(token).before(new Date()); //compara a data de expiração com o tempo atual
    }

    private Date extractExpiration(String token){ //pega a data de expiração
        return extractClaims(token, Claims::getExpiration);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token); //pega todos os dados(basicamente q esta dento de um JSON)
        return claimsResolver.apply(claims); //aplica na função desejada
    }

    private Claims extractAllClaims(String token){ //decodifica o token jwt e retorna os dados
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey()) //a mesma chave de assinatura
                .build()
                .parseClaimsJws(token) //faz a validação
                .getBody(); //retorna as informações
    }

    public Key getSignKey(){ //converte a string em uma chave
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
