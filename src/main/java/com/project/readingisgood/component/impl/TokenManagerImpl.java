package com.project.readingisgood.component.impl;

import com.project.readingisgood.component.TokenManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class TokenManagerImpl implements TokenManager {

    @Value("${jwt.credentials.issuer}")
    private String issuer;

    @Value("${jwt.credentials.duration}")
    private int duration;

    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Override
    public String generateToken(String userName) {

        long nowInMillis = System.currentTimeMillis();
        String jws = Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date(nowInMillis))
                .setIssuer(this.issuer)
                .setExpiration(new Date(nowInMillis + this.duration))
                .signWith(this.key)
                .compact();
        return jws;
    }

    @Override
    public Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody();
    }

    @Override
    public boolean validateToken(String token) {
        if(getUserNameFromToken(token) != null) return true;
        return false;
    }

    @Override
    public String getUserNameFromToken(String token) {
        if(isExpired(token)) return null;
        return parseToken(token).getSubject();
    }

    @Override
    public boolean isExpired(String token) {
        if(parseToken(token).getExpiration().before(new Date(System.currentTimeMillis()))) return true;
        return false;
    }

}
