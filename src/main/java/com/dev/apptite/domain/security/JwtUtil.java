package com.dev.apptite.domain.security;

import com.dev.apptite.api.controller.usuario.response.PerfilResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final SecretKey SECRET_KEY = generateSecretKey();
    private static final String SECRET_STRING = getSecretString();
    private static final long EXPIRATION_TIME = 864_000_000;

    private static SecretKey generateSecretKey() {
        return Jwts.SIG.HS512.key().build();
    }

    private static String getSecretString() {
        return Encoders.BASE64.encode(SECRET_KEY.getEncoded());
    }

    public static String generateToken(String username, PerfilResponse profile) {
        return Jwts.builder()
                .subject(username)
                .claim("id", profile.getId())
                .claim("nome", profile.getNome())
                .claim("idRestaurante", profile.getIdRestaurante())
                .claim("email", profile.getEmail())
                .claim("role", profile.getRole().name())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String extractUsername(String token) {
        SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_STRING));
        return Jwts.parser().verifyWith(secret).build().parseSignedClaims(token).
                getPayload().getSubject();
    }
}