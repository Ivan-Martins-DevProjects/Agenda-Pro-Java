package com.martins.Agenda_Pro.login.services.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.martins.Agenda_Pro.repository.login.table.User;
import com.martins.Agenda_Pro.services.login.LoginService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoginServiceIntegrationTest {

  @Autowired
  private LoginService loginService;

  @Test
  void testBuscarPorEmail() {
    // Esse email precisa existir no banco!
    String email = "admin@email.com";

    User user = loginService.buscarPorEmail(email);

    assertNotNull(user);
    assertEquals(email, user.getEmail());
  }
}
