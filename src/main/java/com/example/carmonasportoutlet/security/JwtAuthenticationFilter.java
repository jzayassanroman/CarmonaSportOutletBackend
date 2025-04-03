package com.example.carmonasportoutlet.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private  JwtService jwtService;
    private  UserDetailsService userDetailsService;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        // Excluir las rutas que no requieren autenticación
//        if (request.getRequestURI().startsWith("/auth/")) {
//            filterChain.doFilter(request, response);  // No aplicar filtro JWT a rutas /auth/
//            return;
//        }




@Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain) throws ServletException, IOException {
    String requestPath = request.getServletPath();

    // EXCLUIR rutas públicas del filtro JWT
    if (requestPath.equals("/auth/register") || requestPath.equals("/auth/login")) {
        logger.info("Skipping JWT filter for: " + requestPath);
        filterChain.doFilter(request, response);
        return;
    }

    String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        logger.info("No Bearer token found in the request.");
        filterChain.doFilter(request, response);
        return;
    }

    String token = authHeader.substring(7);
    String username = jwtService.extractUsername(token);

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        logger.info("Attempting to authenticate user: " + username);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtService.isTokenValid(token, userDetails)) {
            logger.info("Token is valid, setting authentication.");
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);
        } else {
            logger.warn("Token is not valid.");
        }
    }

    filterChain.doFilter(request, response);
}

}

