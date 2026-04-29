package com.martins.Agenda_Pro.controller.entities;

import com.martins.Agenda_Pro.responses.Response;

public class LoginResponse extends Response {
  public LoginResponse(String status, String token) {
    super(status, token);
  }
}
