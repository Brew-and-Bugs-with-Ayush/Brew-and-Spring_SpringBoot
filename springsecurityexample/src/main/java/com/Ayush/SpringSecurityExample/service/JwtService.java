package com.Ayush.SpringSecurityExample.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
class JwtService {

    private String secretKey = "";

    public JwtService(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGenerator.generateKey();

            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String generateToken(String username){

        Map<String , Object> claims = new HashMap<>();

        try {
           return Jwts.builder()
                    .claims()
                    .add(claims)
                    .subject(username)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                    .and()
                    .signWith(getKey())
                    .compact();
        }
        catch (Exception e){
            throw new IllegalArgumentException(e);
        }
    }

    private Key getKey() {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(secretKey);

            return Keys.hmacShaKeyFor(keyBytes);
        }
        catch (Exception e){
            throw new IllegalArgumentException(e);
        }
    }
}
