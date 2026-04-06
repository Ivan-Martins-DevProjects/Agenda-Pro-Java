package com.martins.Agenda_Pro.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class JwtEncodeTest {
  private final JwtEncode jwtEncode = new JwtEncode();
  private final JwtDecode jwtDecode = new JwtDecode();

  @Test
  void deveGerarTokenComFormatoValido() {
    String token = jwtEncode.generateToken(new HashMap<>());
    assertNotNull(token);

    String[] tokenParts = token.split("\\.");
    assertEquals(3, tokenParts.length);
  }

  @Test
  void deveGerarTokenComClaimsValidas() {
    Map<String, String> claims = new HashMap<>();
    claims.put("role", "ADMIN");

    String token = jwtEncode.generateToken(claims);

    String role = jwtDecode.extractRole(token);

    assertEquals("ADMIN", role);
  }
}
