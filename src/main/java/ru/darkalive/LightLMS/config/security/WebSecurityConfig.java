package ru.darkalive.LightLMS.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ru.darkalive.LightLMS.config.security.CustomUserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/scripts/**", "/styles/**", "/images/**", "/icons/**", "/libs/**"
                        ).permitAll()
                        .requestMatchers(
                                "/", "/home/**", "/theory/**", "/examples/**", "/tasks/**", "/test/**"
                        ).authenticated()
                        .requestMatchers(
                                "/journal/**", "/h2-console/**", "/students/**", "/students?**", "/groups/**", "/groups?**"
                        ).hasRole("Преподаватель")
                        .anyRequest().hasRole("Администратор")
                )
                .formLogin((form) -> form.loginPage("/login").defaultSuccessUrl("/").permitAll())
                .logout((logout) -> logout.permitAll());
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }

    /*@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/scripts/**", "/styles/**", "/images/**").permitAll()
                        .requestMatchers("/home/**", "/theory/**", "/examples/**", "/tasks/**", "/test/**").hasRole("Студент")
                        .requestMatchers("/journal/**").hasRole("Преподаватель")
                        .anyRequest().hasRole("Администратор")
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout((logout) -> logout.permitAll());

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .and()
                .build();
    }*/

    /*@Bean
    public AuthenticationManagerBuilder configureAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("SELECT fullname, password, active FROM subject_user WHERE fullname=?")
                .authoritiesByUsernameQuery("SELECT u.fullname, ur.name FROM subject_user u INNER JOIN user_role ur ON u.role_id = ur.id WHERE u.fullname=?");

        return auth;
    }*/
}
