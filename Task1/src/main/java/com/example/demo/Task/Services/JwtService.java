package com.example.demo.Task.Services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.Task.Entity.User;
import com.example.demo.Task.Enum.UserRoles;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	private  final String secreteKey="aVerySecureAndLongSecretKeyForJWTswa1234567890";
	public String generateToken(User user,UserRoles role) {
Map<String , Object> claims = new HashMap<>();
claims.put("role", role);
		return Jwts.builder()
                .setClaims(claims) 
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() +3600000))
				
				.signWith(SignatureAlgorithm.HS256,secreteKey)
				.compact();	
	}
	 public String extractRole(String token) {
	        Claims claims = extractAllClaims(token);
	        return claims.get("role", String.class); // Assuming "role" is a claim in the token
	    }
	
	// 2. Extract Username from Token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 3. Check if Token is Valid
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // 4. Check if Token is Expired
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 5. Extract Expiration Date from Token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 6. Extract a Specific Claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
 // Validate Token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token); // Extract username from token
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    // Helper: Extract All Claims
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secreteKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
