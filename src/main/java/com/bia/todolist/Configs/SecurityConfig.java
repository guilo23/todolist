package com.bia.todolist.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_MATCHERS = {
            "/"
    };

    private static final String[] PUBLIC_MATCHERS_POST = {
            "/user", "/login"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.disable());
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
            .requestMatchers(PUBLIC_MATCHERS).permitAll()
            .anyRequest().authenticated());
        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}