package com.martins.Agenda_Pro.services.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDTO {

  @NotBlank(message = "Insira um email válido")
  @Email(message = "Email inválido")
  @Size(max = 100, message = "Limite de 100 caracteres atingido")
  private String email;

  @NotBlank(message = "Insira uma senha válida")
  @Size(max = 100, message = "Limite de 100 caracteres atingido")
  private String password;

  // Construtor vazio necessário para o Spring-Boot
  public LoginRequestDTO() {
  };

  // Getters
  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  // Setters
  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
