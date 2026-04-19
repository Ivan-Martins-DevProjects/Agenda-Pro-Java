package com.martins.Agenda_Pro.errors;

public class PasswordHashException extends RuntimeException {

  public PasswordHashException(String message, Throwable cause) {
    super(message, cause);
  }
}
