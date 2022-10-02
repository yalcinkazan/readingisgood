package com.project.readingisgood.component;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

@Component
public interface TokenManager {

    String generateToken(String userName);

    Claims parseToken(String token);

    boolean validateToken(String token);

    String getUserNameFromToken(String token);

    boolean isExpired(String token);


}
