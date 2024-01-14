package com.springboot.blogApp.jwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_Key = "cc1fb5f2cbc4e0ef06b5977d4e785e4a709864c4b473aeeb3e0e64609aaf01fb";
    private static final long JWT_Token_Validity = 5*60*60;
    public String extractUsernameFromToken(String token)
    {
        return extractClaims(token, Claims::getSubject);
    }
    public boolean isTokenValid(String token, UserDetails userDetails)
    {
        final String userName =extractUsernameFromToken(token);
        return(userName).equals(userDetails.getUsername()) && !isTokenExpire(token);
    }

    public String generateToken(UserDetails userDetails)
    {
        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(
            Map<String, Object> extraClaims, UserDetails userDetails)
    {
        return
                Jwts
                        .builder()
                        .setClaims(extraClaims)
                        .setSubject(userDetails.getUsername())
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis()+1000*60*5))
                        .signWith(getSinginKey(), SignatureAlgorithm.HS256)
                        .compact();
    }


    private boolean isTokenExpire(String token)
    {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token )
    {
        return extractClaims(token, Claims::getExpiration);
    }
    public <T> T  extractClaims(String token, Function<Claims, T> claimsResolver)
    {
        final Claims claim = extractAllClaims(token);
        return claimsResolver.apply(claim);
    }
    private Claims extractAllClaims(String token)
    {
        return
                Jwts.parserBuilder()
                        .setSigningKey(getSinginKey())
                        .build().parseClaimsJws(token).getBody();
    }

    private Key getSinginKey() {
        byte[]keyBytes = Decoders.BASE64.decode(SECRET_Key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
