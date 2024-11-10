package com.backend.train_booking_backend.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.backend.train_booking_backend.models.AppUser;
import com.backend.train_booking_backend.repositories.UserRepository;
import com.backend.train_booking_backend.models.enums.ERole;

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
        claims.put("fullName", user.getFullName());
        claims.put("phoneNumber", user.getPhoneNumber());
        claims.put("address", user.getAddress());
        claims.put("identifyCard", user.getIdentifyCard());
        claims.put("role", user.getRole().name());

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
    	String email = extractEmail(token);
        AppUser user = userRepository.findUserByEmail(email);

        if (user == null || !token.equals(user.getLastToken())) {
            return false;
        }

        Claims claims = extractAllClaims(token);
        String roleFromToken = claims.get("role", String.class);
        ERole role = ERole.valueOf(roleFromToken);

        if (!role.equals(user.getRole())) {
            return false;
        }

        return !isTokenExpired(token);
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





