package com.martins.Agenda_Pro.services.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RecoveryPasswordDTO {
  @NotBlank(message = "Email inválido")
  @Email(message = "Email inválido")
  @Size(max = 100, message = "Limite de 100 caracteres atingido")
  private String email;

  public RecoveryPasswordDTO() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
