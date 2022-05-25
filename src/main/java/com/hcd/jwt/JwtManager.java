package com.hcd.jwt;

import com.hcd.jwt.strategy.ValidationStrategy;

public interface JwtManager {

    String generate(String sub, String iss, String aud);

    /**
     * @deprecated in favor of {@link #isValid(String, ValidationStrategy)}
     */
    @Deprecated(forRemoval = true)
    boolean isValid(String jwt, String iss, String aud);

    boolean isValid(String jwt, ValidationStrategy strategy);
}
