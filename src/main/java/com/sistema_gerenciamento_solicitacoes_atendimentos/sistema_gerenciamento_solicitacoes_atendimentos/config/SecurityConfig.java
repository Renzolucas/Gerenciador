package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Máquina de Criptografia BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {//recebe a senha
        return new BCryptPasswordEncoder(); //retorna cripto
    }
    @Bean   
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desliga a proteção contra formulários falsos (necessário para o Postman)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Libera TODAS as rotas sem pedir senha
            );
        
        return http.build();
    }
}
