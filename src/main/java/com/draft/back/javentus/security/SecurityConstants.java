package com.draft.back.javentus.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Javentus ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/draft/sign-up";
}
