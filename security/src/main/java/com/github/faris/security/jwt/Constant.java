package com.github.faris.security.jwt;

import java.util.concurrent.TimeUnit;

public interface Constant {

    long TOKEN_EXPIRATION_TIME = TimeUnit.DAYS.toMillis(1);

    String TOKEN_SECURE_KEY = "mySecret!!";

    String TOKEN_ISSUER = "Faris";

    String TOKEN_HEADER = "Authorization";

    String TOKEN_PREFIX = "Bearer ";
}
