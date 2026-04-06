package com.martins.Agenda_Pro.security;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtEncode extends JwtUtil {
  private static long EXPIRATION = 1000 * 60 * 60; // Duração de 1 hora

  public String generateToken(Map<String, String> claims) {
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
        .signWith(this.KEY, SignatureAlgorithm.HS256)
        .compact();
  }
}
