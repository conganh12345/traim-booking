package com.backend.train_booking_backend.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.backend.train_booking_backend.models.AppUser;
import com.backend.train_booking_backend.repositories.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtils {
	private final String SECRET_KEY = "your_secret_key"; // Khóa bí mật
    private final long EXPIRATION_TIME = 40000; // 1 ngày

    @Autowired
    private UserRepository userRepository;

//    public String generateToken(String username) {
//        JwtBuilder builder = Jwts.builder()
//            .setSubject(username)
//            .setIssuedAt(new Date())
//            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//            .signWith(SignatureAlgorithm.HS256, SECRET_KEY);
//
//        return builder.compact();
//    }

    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();
        return claims.getSubject();
    }
    
    
    
    public String generateToken(AppUser user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("phoneNumber", user.getPhoneNumber());
//        claims.put("fullName", user.getFullName());
        claims.put("address", user.getAddress());

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(user.getEmail())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }

    // Giải mã token để lấy Claims
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    
    

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractEmail(token);
        AppUser user = userRepository.findUserByEmail(username);

        // Kiểm tra token trong yêu cầu có khớp với token gần nhất đã lưu không
        if (user == null || !token.equals(user.getLastToken())) {
            return false;
        }

        return (!isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }
    
    public boolean isTokenValid(String token) {
        try {
            // Parse the token and check for expiration
            Claims claims = extractAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false; // Token is invalid
        }
    }
	
	
}





