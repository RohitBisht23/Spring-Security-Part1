package com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Filters;

import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Entity.UserEntity;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Service.Impl.UserService;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authentication");

        if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = requestTokenHeader.split("Bearer ")[1];

        Long userId = jwtService.getUserIdFromToke(token);

        if(userId != null) {
            UserEntity userDetails = userService.getUserById(userId);
        }
    }
}
