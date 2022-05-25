package com.hcd.jwt;

import com.hcd.jwt.strategy.DefaultValidationStrategy;
import com.hcd.jwt.strategy.ValidationStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JwtManagerTest {

    private String iss;
    private String aud;
    private String jwt;
    private JwtManager jwtManager;

    @BeforeEach
    void setUp() {
        jwtManager = new JwtManagerImpl();

        iss = "issuer";
        aud = "audience";
        jwt = jwtManager.generate("hcd", iss, aud);
        Assertions.assertNotNull(jwt);
    }

    @Test
    void isValid_tightly_coupled() {
        final boolean valid = jwtManager.isValid(jwt, iss, aud);
        Assertions.assertTrue(valid);
    }

    @Test
    void isValid_loosely_coupled_default_strategy() {
        final ValidationStrategy strategy = new DefaultValidationStrategy(iss, aud);
        final boolean valid = jwtManager.isValid(jwt, strategy);
        Assertions.assertTrue(valid);
    }

    @Test
    void isValid_loosely_coupled_custom_strategy() {
        final boolean valid = jwtManager.isValid(jwt, body -> iss.equals(body.getIssuer()));
        Assertions.assertTrue(valid);
    }
}
