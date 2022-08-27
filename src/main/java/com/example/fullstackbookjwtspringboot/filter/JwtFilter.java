package com.example.fullstackbookjwtspringboot.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        System.out.println("tokenHeader = " + tokenHeader);
        if (tokenHeader != null && tokenHeader.equals("foobar")) {
            System.out.println("foobar found");
        }
        filterChain.doFilter(request, response);
    }
}
