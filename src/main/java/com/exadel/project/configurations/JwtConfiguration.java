package com.exadel.project.configurations;

import com.exadel.project.trainee.entity.AdditionalInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(JwtConfiguration.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.url}")
    private String serverUrl;

    public String generateToken(AdditionalInfo additionalInfo) {
        return serverUrl + Jwts.builder()
                .setId(additionalInfo.getId().toString())
                .setSubject(additionalInfo.getTrainee().getEmail())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (UnsupportedJwtException unsEx) {
            logger.warn("Unsupported jwt", unsEx);
        } catch (Exception e) {
            logger.warn("invalid token", e);
        }
        return false;
    }

    public String getIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getId();
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
