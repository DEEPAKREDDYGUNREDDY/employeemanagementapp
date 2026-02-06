package com.example.securityapp.Service;


import com.example.securityapp.Entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JWTService {


    private String secret = "vfcegbhwdaxcgwdhbjchwdnskjfhcbwnjkschwnskjc";

    private SecretKey getsecretkey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(UserEntity user){

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("email",user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 120))
                .signWith(getsecretkey())
                .compact();
    }

    public String generaterefreshToken(UserEntity user){

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 240))
                .signWith(getsecretkey())
                .compact();
    }


    public String getUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getsecretkey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("email", String.class);
    }

    public Long getId(String token) {
        return Long.parseLong(
                Jwts.parserBuilder()
                        .setSigningKey(getsecretkey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject());
    }


}
