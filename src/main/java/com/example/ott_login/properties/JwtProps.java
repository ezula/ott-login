package com.example.ott_login.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application.jwt")
public record JwtProps(
        String secret,
        long expiration
) { }
