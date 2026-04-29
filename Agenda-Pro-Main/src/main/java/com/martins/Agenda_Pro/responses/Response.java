package com.martins.Agenda_Pro.responses;

public class Response implements ResponseModel {
  private String status;
  private Object message;

  public Response(String status, String message) {

    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public Object getMessage() {
    return message;
  }
}
