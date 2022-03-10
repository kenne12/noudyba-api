package org.kenne.noudybaapi.security;

public class SecurityConstant {

    public static final String SECRET = "epargne_fido_inter@1234__";
    public static final long EXPIRATION_TIME = (30 * 24 * 60 * 1000); // 1/2 DAY
    public static final long EXPIRATION_TIME_REFRESH = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
