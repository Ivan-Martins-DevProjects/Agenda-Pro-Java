package com.martins.Agenda_Pro.services.login;

import org.springframework.context.annotation.Bean;

public class LoginValidator {

  @Bean
  public static void validate(LoginRequestDTO request) {
    if (request.getEmail() == null || request.getEmail().isBlank()) {
      throw new IllegalArgumentException("email não encontrado");
    }

    if (request.getPassword() == null || request.getPassword().isBlank()) {
      throw new IllegalArgumentException("Senha não encontrada");
    }
  }
}
