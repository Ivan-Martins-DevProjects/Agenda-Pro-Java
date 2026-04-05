package com.martins.Agenda_Pro.security;

import io.jsonwebtoken.*;

public class JwtDecode extends JwtUtil {

  // Extrai a role do usuário
  public String extractRole(String token) {
    return (String) getClaims(token).get("role");
  }

  // Realiza a validação do token
  public boolean isValid(String token) {
    try {
      getClaims(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

  // Classe faz parsing e validação do token retornando as claims
  // valores podem ser acessados com: claims.get("key")
  private Claims getClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(this.KEY)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }
}
