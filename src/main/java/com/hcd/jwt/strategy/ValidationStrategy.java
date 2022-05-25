package com.hcd.jwt.strategy;

import io.jsonwebtoken.Claims;

public interface ValidationStrategy {

    boolean isValid(Claims body);
}
