package com.martins.Agenda_Pro.errors;

public class UserNotFound extends MainError {

  public UserNotFound(String status, int statusCode, String message) {
    super(status, statusCode, message);
  }
}
