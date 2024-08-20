package org.nimbus.vanguard.filters;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

public class JWTGeneratorFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY = "ABkqGMRhFt1YRd3HGl1+J9KJvO7fDzD50yC8MvJ4+Gw=";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        String secretKey = getEnvironment().getProperty("jwt.secretKey", SECRET_KEY);
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder().issuer("Vanguard").subject("JWT Token").claim("username", authentication.getName())
                .claim("authorities", authorities).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(key).compact();
        response.setHeader("Authorization", "Bearer " + jwt);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().startsWith("/user");
    }
}
