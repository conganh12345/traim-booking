package com.backend.train_booking_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.backend.train_booking_backend.security.JwtRequestFilter;
import com.backend.train_booking_backend.services.impl.UserDetailsServiceImplement;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	 private final UserDetailsServiceImplement userDetailsServiceThuc;
	    private final JwtRequestFilter jwtRequestFilter;

	    @Autowired
	    public SecurityConfig(UserDetailsServiceImplement userDetailsServiceThuc, JwtRequestFilter jwtRequestFilter) {
	        this.userDetailsServiceThuc = userDetailsServiceThuc;
	        this.jwtRequestFilter = jwtRequestFilter;
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf(AbstractHttpConfigurer::disable) 
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/api/auth/**").permitAll() 
	                .requestMatchers("/api/booking/statistics").permitAll() 
	                .requestMatchers("/api/seat/list/**").permitAll() 
	                .requestMatchers("/api/coach/list/**").permitAll() 
	                .requestMatchers("/api/schedule/id/**").permitAll() 
	                .requestMatchers("/api/schedule/search/**").permitAll() 
	                .requestMatchers("/api/vnpay/**").permitAll()
	                .requestMatchers("/api/user/**").hasAuthority("USER")
	                .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
	                .anyRequest().authenticated()) 
	            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); 

	        return http.build(); 
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder(); // Sử dụng BCrypt để mã hóa mật khẩu
	    }

	    @Bean
	    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
	        AuthenticationManagerBuilder authenticationManagerBuilder =
	            http.getSharedObject(AuthenticationManagerBuilder.class);
	        authenticationManagerBuilder.userDetailsService(userDetailsServiceThuc).passwordEncoder(passwordEncoder()); // Thiết lập dịch vụ người dùng và bộ mã hóa mật khẩu
	        return authenticationManagerBuilder.build(); // Xây dựng AuthenticationManager
	    }
	
}
