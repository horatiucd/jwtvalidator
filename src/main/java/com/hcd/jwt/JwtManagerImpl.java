package com.hcd.jwt;

import com.hcd.jwt.strategy.ValidationStrategy;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtManagerImpl implements JwtManager {

    private static final long EXPIRATION_PERIOD = 60_000;
    private static final String SECRET = "s1e2c3r4e5t6k7e8y9";

    @Override
    public String generate(String sub, String iss, String aud) {
        final Date exp = new Date(System.currentTimeMillis() + EXPIRATION_PERIOD);

        return Jwts.builder()
                .setSubject(sub)
                .setIssuer(iss)
                .setAudience(aud)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /**
     * @deprecated in favor of {@link #isValid(String, ValidationStrategy)}
     */
    @Deprecated(forRemoval = true)
    @Override
    public boolean isValid(String jwt, String iss, String aud) {
        Claims body;
        try {
            body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (JwtException e) {
            return false;
        }

        return iss.equals(body.getIssuer()) &&
                aud.equals(body.getAudience());
    }

    @Override
    public boolean isValid(String jwt, ValidationStrategy strategy) {
        Claims body;
        try {
            body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (JwtException e) {
            return false;
        }

        return strategy.isValid(body);
    }
}
