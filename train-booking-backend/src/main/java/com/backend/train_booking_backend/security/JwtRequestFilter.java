package com.backend.train_booking_backend.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils; // Để sử dụng các phương thức xử lý JWT

	@Autowired
	private UserDetailsService userDetailsService; // Để tải thông tin người dùng

	@Autowired
    public JwtRequestFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);

        if (token != null && jwtUtils.isTokenValid(token)) {
            String email = jwtUtils.extractEmail(token); 
            UserDetails userDetails = userDetailsService.loadUserByUsername(email); 

            Claims claims = jwtUtils.extractAllClaims(token);
            String roleFromToken = claims.get("role", String.class); 

            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleFromToken); 

            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Collections.singletonList(grantedAuthority));
            SecurityContextHolder.getContext().setAuthentication(authentication); 
        }

        filterChain.doFilter(request, response); 
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); 
        }
        return null;
    }

}
