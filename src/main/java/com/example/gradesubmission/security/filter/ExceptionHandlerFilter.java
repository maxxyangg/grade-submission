package com.example.gradesubmission.security.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.gradesubmission.exception.EntityNotFoundException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (EntityNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Username does not exist");
            response.getWriter().flush();
        } catch (JWTDecodeException ex) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("JWT Not Valid");
            response.getWriter().flush();
        }
        catch (RuntimeException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Username does not exist");
            response.getWriter().flush();
        } 
    }

}
