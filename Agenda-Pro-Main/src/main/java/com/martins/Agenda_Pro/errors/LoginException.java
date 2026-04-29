package com.martins.Agenda_Pro.errors;

public class LoginException extends MainError {

  public LoginException(String status, int statusCode, String message) {
    super(status, statusCode, message);
  }
}
