package org.elis.eventsmanager.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.elis.eventsmanager.model.User;
import org.elis.eventsmanager.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.awt.*;
import java.security.Key;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class TokenUtil {

    @Autowired
    UserService userService;

    @Value("${miocodice.jwt.key}")
    private String key;

    private SecretKey generateKey(){
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    public String generateToken(User u){
        String role = u.getRole().toString();
        String username = u.getEmail();
        String birthDate = u.getBirthDate().format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy"));

        long duration = 1000L*60*60*24*60;

        return Jwts.builder().claims()
                .add("ruolo", role)
                .add("birthDate", birthDate)
                .add("username", username)
                .add("role", role)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+duration))
                .and()
                .signWith(generateKey())
                .compact();


    }

    private Claims getClaimsFromToken(String token){
         return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public String getSubject(String token){
        return getClaimsFromToken(token).getSubject();
    }

    public User getUserFromToken(String token){
        String username = getSubject(token);
        return userService.findByEmail(username);
    }

    public LocalDate getBirthDate(String token){
        String birthDate = getClaimsFromToken(token).get("birthDate", String.class);
        return LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy"));
    }

    public String getRole(String token){
        return getClaimsFromToken(token).get("role", String.class);

    }

}
