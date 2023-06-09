package ru.darkalive.LightLMS.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/scripts/**", "/styles/**", "/images/**", "/icons/**", "/libs/**", "/favicon.ico"
                        ).permitAll()
                        .requestMatchers(
                                "/", "/home/**", "/subjects/**", "/api/openai/**", "/openai/**", "/logo/**", "/performance/**"
                        ).authenticated()
                        .requestMatchers(
                                "/api/**"
                        ).hasRole("Преподаватель")
                        .anyRequest().hasRole("Администратор")
                )
                .formLogin((form) -> form.loginPage("/login").defaultSuccessUrl("/").failureUrl("/login/error").permitAll())
                .logout((logout) -> logout.permitAll());
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }
}
