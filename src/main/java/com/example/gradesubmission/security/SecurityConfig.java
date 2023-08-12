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
import org.springframework.security.config.*;

@Configuration
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) //cross site request forgery
                .authorizeHttpRequests((authz) -> authz
                .requestMatchers(HttpMethod.POST).hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET).permitAll()
                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
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
