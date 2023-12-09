package com.springboot.blogApp.security.config.jwtConfig;

import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenHelper {
    public static final long JWT_Token_Validity = 5*60*60;
    private String secret = "jwtTokenKey";
    public String getUsernameFromToken(String token)
    {
        return getClaimFromToken(token, Claims::getSubject);
    }
    public Date getExpirationDateFromToken(String token )
    {
        return getClaimFromToken(token, Claims::getExpiration)
    }
    public <T> getClaimFromToken(String token, Function<Claims, T>claimsResolver)
    {

    }
}
