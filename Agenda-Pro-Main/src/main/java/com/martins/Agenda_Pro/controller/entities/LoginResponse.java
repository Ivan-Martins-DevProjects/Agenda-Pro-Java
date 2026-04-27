package com.martins.Agenda_Pro.controller.entities;

import com.martins.Agenda_Pro.responses.Response;

public class LoginResponse extends Response {
  public LoginResponse(String status, int statusCode, String token) {
    super(null, status, statusCode, token);
  }
}
