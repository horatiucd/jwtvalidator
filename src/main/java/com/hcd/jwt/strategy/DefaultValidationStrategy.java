package com.hcd.jwt.strategy;

import io.jsonwebtoken.Claims;

public class DefaultValidationStrategy implements ValidationStrategy {

    private final String iss;
    private final String aud;

    public DefaultValidationStrategy(String iss, String aud) {
        this.iss = iss;
        this.aud = aud;
    }

    @Override
    public boolean isValid(Claims body) {
        return iss.equals(body.getIssuer()) &&
                aud.equals(body.getAudience());
    }
}
