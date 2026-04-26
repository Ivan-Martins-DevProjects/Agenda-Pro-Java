package com.martins.Agenda_Pro.responses;

public class ErrorResponse implements ResponseModel {
  private String status;
  private String message;

  public ErrorResponse(String status, String message) {
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
