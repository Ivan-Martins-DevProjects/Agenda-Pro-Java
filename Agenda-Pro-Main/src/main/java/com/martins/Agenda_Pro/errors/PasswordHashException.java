package com.martins.Agenda_Pro.errors;

public class PasswordHashException extends MainError {

  public PasswordHashException(String status, int statusCode, String message) {
    super(status, statusCode, message);
  }
}
