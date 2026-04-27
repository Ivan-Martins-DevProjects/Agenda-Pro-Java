package com.martins.Agenda_Pro.errors;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.martins.Agenda_Pro.responses.Response;
import com.martins.Agenda_Pro.responses.ResponseModel;

@Component
public class ErrorHandler {

  public ResponseModel getResponse(Exception errorInstance) {
    if (errorInstance instanceof MainError e) {
      return getMainResponse(e);
    }

    if (errorInstance instanceof MethodArgumentNotValidException e) {
      return getValidatorResponse(e);
    }

    return new Response(null, "error", 500, errorInstance.getMessage());
  }

  private ResponseModel getMainResponse(MainError error) {
    String status = error.getStatus();
    String message = error.getMessage();
    return new Response(null, status, error.getStatusCode(), message);
  }

  private ResponseModel getValidatorResponse(MethodArgumentNotValidException error) {
    InvalidField newError = new InvalidField(error.getFieldErrors());

    return new Response(
        newError.getResponse(),
        newError.getStatus(),
        newError.getStatusCode(),
        null);
  }
}
