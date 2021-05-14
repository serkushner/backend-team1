package com.exadel.project.interview.service;

import com.exadel.project.interview.entity.Interview;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtInterviewService {

    @Value("${jwt.secret.interview}")
    private String jwtSecret;

    @Value("${jwt.url.interview}")
    private String interviewUrl;

    public String generateToken(Interview interview){
        return interviewUrl + Jwts.builder()
                .setId(interview.getId().toString())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
        } catch (UnsupportedJwtException unsEx) {
            log.warn("Unsupported jwt", unsEx);
            throw unsEx;
        } catch (Exception e) {
            log.warn("invalid token", e);
            throw e;
        }
    }

    public String getIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getId();
    }
}