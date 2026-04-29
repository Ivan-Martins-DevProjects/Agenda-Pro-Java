package com.martins.Agenda_Pro.errors;

public class MainError extends RuntimeException {
  private final int StatusCode;
  private final String Status;
  private final String Message;

  public MainError(String status, int statusCode, String message) {
    super(message);
    this.Message = message;
    this.StatusCode = statusCode;
    this.Status = status;
  }

  public int getStatusCode() {
    return StatusCode;
  }

  public String getStatus() {
    return Status;
  }

  public String getMessage() {
    return Message;
  }
}
