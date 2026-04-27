package com.martins.Agenda_Pro.responses;

import java.util.Map;

public class Response implements ResponseModel {
  private String status;
  private Object message;
  private int statusCode;

  public Response(Map<String, String> dictionary) {
    this(dictionary, null, 0, null);
  }

  public Response(Map<String, String> dictionary, String status, int statusCode, String message) {
    if (dictionary != null && message == null) {
      this.message = dictionary;
    }

    this.status = status;
    this.message = message;
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getStatus() {
    return status;
  }

  public Object getMessage() {
    return message;
  }
}
