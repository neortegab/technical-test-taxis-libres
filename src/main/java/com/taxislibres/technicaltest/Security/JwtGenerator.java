package com.taxislibres.technicaltest.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JwtGenerator {

    private final static String jwtSecret = "secret";
    public String generateToken(Authentication authentication){
        var email = authentication.getName();
        var currentDate = LocalDateTime.now();
        var expireDate = LocalDateTime.now().plusDays(1);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(currentDate.getSecond()))
                .setExpiration(new Date(expireDate.getSecond()))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserName(String token){
        var claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("Token expired or incorrect");
        }
    }
}
