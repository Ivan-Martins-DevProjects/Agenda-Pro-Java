package com.martins.Agenda_Pro.errors;

public class MainError extends RuntimeException {
  private final int Status;

  public MainError(int status, String message) {
    super(message);
    this.Status = status;
  }

  public int getStatus() {
    return Status;
  }

}
