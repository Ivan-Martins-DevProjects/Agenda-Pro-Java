package com.martins.Agenda_Pro.controller.entities;

import com.martins.Agenda_Pro.responses.ResponseModel;

public class LoginResponse implements ResponseModel {
  private String status;
  private String message;

  public LoginResponse(String status, String token) {
    this.status = status;
    this.message = token;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
