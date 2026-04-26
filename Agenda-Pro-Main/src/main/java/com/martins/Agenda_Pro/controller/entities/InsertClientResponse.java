package com.martins.Agenda_Pro.controller.entities;

import com.martins.Agenda_Pro.responses.ResponseModel;

public class InsertClientResponse implements ResponseModel {
  private String status;
  private String message;

  public InsertClientResponse(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
