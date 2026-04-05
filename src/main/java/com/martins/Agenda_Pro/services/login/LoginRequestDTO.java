package com.martins.Agenda_Pro.services.login;

public class LoginRequestDTO {

  private String username;
  private String password;

  // Construtor vazio necessário para o Spring-Boot
  public LoginRequestDTO() {
  };

  // Getters
  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  // Setters
  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
