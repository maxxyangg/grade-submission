package com.example.gradesubmission.security.filter;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.gradesubmission.security.Prefences;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    // Header will be -> Authorization: Bearer JWT
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String header = request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        
        String token = header.replace("Bearer ", ""); // We just want the JWT so we get rid of Bearer text
        String user = JWT.require(Algorithm.HMAC512(Prefences.KEY))
                .build()
                .verify(token)
                .getSubject();
        
                System.out.println(user);
                
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList()); // MUST USE THIS CONSTRUCTOR AND NOT THE OTHER
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

}
