package com.example.gradesubmission.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.security.config.*;

@Configuration
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers.disable()) 
                .csrf(csrf -> csrf.disable()) //cross site request forgery
                .authorizeHttpRequests((authz) -> authz
                    .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                    .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                    .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                
        return http.build();
    }

    @Bean
    public UserDetailsService Users() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.BCryptEncoder().encode("password"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.BCryptEncoder().encode("user-password"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);

    }
}
