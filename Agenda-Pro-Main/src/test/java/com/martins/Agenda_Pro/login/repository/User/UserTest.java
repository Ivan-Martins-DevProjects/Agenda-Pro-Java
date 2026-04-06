package com.martins.Agenda_Pro.login.repository.User;

import org.junit.jupiter.api.Test;

import com.martins.Agenda_Pro.repository.login.table.User;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

  @Test
  void testCreateJwtClaims() {
    // Cria um usuário de teste
    User user = new User();
    user.setId("1");
    user.setEmail("teste@ex.com");
    user.setPassword("123456");
    user.setRole("USER");
    user.setInstance("inst1");
    user.setName("Teste");
    user.setBussinessId("bus123");

    // Gera as claims
    Map<String, String> claims = user.CreateJwtClaims();

    // Verifica se o map contém os valores corretos
    assertEquals("1", claims.get("id"));
    assertEquals("teste@ex.com", claims.get("email"));
    assertEquals("123456", claims.get("password"));
    assertEquals("USER", claims.get("role"));
    assertEquals("inst1", claims.get("instance"));
    assertEquals("Teste", claims.get("name"));
    assertEquals("bus123", claims.get("bussinessId"));

    // Verifica se o tamanho do map é igual ao número de campos
    assertEquals(7, claims.size());
  }
}
