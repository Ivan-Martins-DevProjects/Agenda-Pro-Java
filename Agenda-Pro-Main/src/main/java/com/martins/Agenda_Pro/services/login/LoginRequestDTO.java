package com.martins.Agenda_Pro.services.login;

public class LoginRequestDTO {

  private String email;
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
