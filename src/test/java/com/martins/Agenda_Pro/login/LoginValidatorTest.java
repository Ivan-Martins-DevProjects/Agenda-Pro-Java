package com.martins.Agenda_Pro.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.martins.Agenda_Pro.services.login.LoginRequestDTO;
import com.martins.Agenda_Pro.services.login.LoginValidator;

class LoginValidatorTest {

  @Test
  void deveLancarErroQuandoUsernameForNulo() {
    LoginRequestDTO dto = new LoginRequestDTO();
    dto.setPassword("password");

    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
      LoginValidator.validate(dto);
    });

    assertEquals("Username não encontrado", ex.getMessage());
  }

  @Test
  void deveLancarErroQuandoPasswordForNulo() {
    LoginRequestDTO dto = new LoginRequestDTO();
    dto.setUsername("Username");

    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
      LoginValidator.validate(dto);
    });

    assertEquals("Senha não encontrada", ex.getMessage());
  }
}
