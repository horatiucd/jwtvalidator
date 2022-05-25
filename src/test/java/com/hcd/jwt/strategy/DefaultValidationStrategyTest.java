package com.hcd.jwt.strategy;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class DefaultValidationStrategyTest {

    private String iss;
    private String aud;
    private ValidationStrategy strategy;

    @BeforeEach
    void setUp() {
        iss = "issuer";
        aud = "audience";
        strategy = new DefaultValidationStrategy(iss, aud);
    }

    @Test
    void isValid() {
        Claims body = Mockito.mock(Claims.class);

        when(body.getIssuer()).thenReturn(iss);
        when(body.getAudience()).thenReturn(aud);

        boolean valid = strategy.isValid(body);
        Assertions.assertTrue(valid);

        verify(body).getIssuer();
        verify(body).getAudience();

        verifyNoMoreInteractions(body);
    }
}
