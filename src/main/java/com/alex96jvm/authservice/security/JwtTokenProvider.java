package com.alex96jvm.authservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-in-hours}")
    private long hours;

    public String createToken(UUID uuid, String login, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("uuid", uuid);
        claims.put("login", login);
        claims.put("role", role);

                return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 1000 * 60 * 60 * hours))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)), Jwts.SIG.HS256)
                .compact();
    }
}
