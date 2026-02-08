package org.letsplay.letsplay.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.letsplay.letsplay.Exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends  OncePerRequestFilter {
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try{
            var header = request.getHeader("Authorization");
            if(header == null || !header.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
            }
        } catch (RuntimeException e) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
