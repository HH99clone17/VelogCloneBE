package com.example.velogclonebe.config.jwt;

public interface JwtProperties {
    String SECRET = "hh99clone17";
    int EXPIRATION_TIME = 60000*10;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
