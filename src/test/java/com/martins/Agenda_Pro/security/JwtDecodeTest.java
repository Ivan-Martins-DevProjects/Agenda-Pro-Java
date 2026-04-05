package com.martins.Agenda_Pro.security;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtDecodeTest {

  private final JwtEncode jwtEncode = new JwtEncode();
  private final JwtDecode jwtDecode = new JwtDecode();

  private Map<String, String> createClaims(String role) {
    Map<String, String> claims = new HashMap<>();
    claims.put("role", role);
    claims.put("user", "ivan");
    return claims;
  }

  @Test
  void deveGerarEExtrairRole() {
    String token = jwtEncode.generateToken(createClaims("ADMIN"));

    String role = jwtDecode.extractRole(token);

    assertEquals("ADMIN", role);
  }

  @Test
  void deveValidarTokenGerado() {
    String token = jwtEncode.generateToken(createClaims("USER"));

    assertTrue(jwtDecode.isValid(token));
  }

  @Test
  void deveInvalidarTokenAlterado() {
    String token = jwtEncode.generateToken(createClaims("USER"));

    // quebra o token propositalmente
    token = token + "qualquercoisa";

    assertFalse(jwtDecode.isValid(token));
  }
}
