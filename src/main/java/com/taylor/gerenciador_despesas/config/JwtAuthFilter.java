package com.taylor.gerenciador_despesas.config;

import com.taylor.gerenciador_despesas.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private  final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JwtAuthFilter executado para: " + req.getRequestURI());
        final String authHeader = req.getHeader("Authorization"); //capturamos os header para extrair o token q vem assim (Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6...)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) { //verificamos e o toke existe e se é um Bearer
            filterChain.doFilter(req, res);
            return;
        }
        final String jwt = authHeader.substring(7); //extrai o token tirando o a palavra Bearer removendo so 7 caracteres
        final String username = jwtService.extractUsername(jwt); //extrai o subject para saber se o token é valido, se sim ele vai conseguir pegar o dono desse token

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){ //verifica se o username existe e se não esta autenticado
            UserDetails userDetails = userDetailsService.loadUserByUsername(username); //busca esse username
            if (jwtService.isTokenValid(jwt, userDetails)) { //verifica se o token é valido
                var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken); //autentica ele
                System.out.println("Autoridades: " + userDetails.getAuthorities());
            }
        }
        filterChain.doFilter(req, res); //serve para dizer que pode passar para o proximo filtro ou ir para a controller
        //Obs(caso n chame ele, ele ira para requisição e ela n seguirar em frente)
    }
}
