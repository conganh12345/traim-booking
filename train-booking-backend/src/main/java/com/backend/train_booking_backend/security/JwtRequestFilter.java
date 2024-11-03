package com.backend.train_booking_backend.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.backend.train_booking_backend.services.impl.UserDetailsServiceImplement;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils; // Để sử dụng các phương thức xử lý JWT

	@Autowired
	private UserDetailsServiceImplement userDetailsService; // Để tải thông tin người dùng

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization"); // Lấy header Authorization

		String username = null;
		String jwt = null;

		// Kiểm tra xem header có tồn tại và bắt đầu bằng "Bearer "
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7); // Bỏ tiền tố "Bearer "
			username = jwtUtils.extractUsername(jwt); // Lấy username từ token
		}

		// Nếu username không null và chưa xác thực
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); // Tải thông tin người dùng
			if (jwtUtils.validateToken(jwt, userDetails)) { // Kiểm tra tính hợp lệ của token
				// Thiết lập xác thực trong SecurityContext
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication); // Lưu xác thực vào context
			}
		}
		chain.doFilter(request, response); // Tiếp tục chuỗi bộ lọc
	}

}
