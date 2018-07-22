package com.lichaoxi.bive.bive.utils;

import com.lichaoxi.bive.bive.security.CustomUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {

    public static String generateToken(CustomUserDetails customUserDetails) {
        return Jwts.builder()
                .setSubject(customUserDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
                .compact();
    }
}
