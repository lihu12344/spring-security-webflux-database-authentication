package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityWebFilterChain initSecurityWebFilterChain(ServerHttpSecurity http){
        http.formLogin().and().authorizeExchange()
                .pathMatchers("/hello2").hasAnyRole("user","admin")
                .pathMatchers("/hello3").hasRole("admin")
                .pathMatchers("/**").permitAll();

        return http.build();
    }

    @Bean
    public PasswordEncoder initPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
