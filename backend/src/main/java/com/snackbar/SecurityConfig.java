package com.snackbar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Desabilita CSRF se necessário
            .authorizeHttpRequests()
            .requestMatchers("/api/payments/**").permitAll() // Permite acesso sem autenticação aos endpoints de pagamentos
            .anyRequest().authenticated(); // Requer autenticação para outros endpoints

        return http.build();
    }
}
