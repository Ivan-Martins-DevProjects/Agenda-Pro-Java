package com.martins.Agenda_Pro.security;

import java.security.Key;

import io.jsonwebtoken.security.Keys;

public class JwtUtil {

  private static final String SECRET = "25609b469399d76b86d3e07dbfd371a2dd2c030c698109700f0faf8739feeae0";
  protected final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
}
