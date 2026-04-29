package com.martins.Agenda_Pro.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.martins.Agenda_Pro.responses.Response;
import com.martins.Agenda_Pro.responses.ResponseModel;

@Component
public class ErrorHandler {

  public ResponseEntity<ResponseModel> getResponse(Exception errorInstance) {
    if (errorInstance instanceof MainError e) {
      return getMainResponse(e);
    }

    if (errorInstance instanceof MethodArgumentNotValidException e) {
      return getValidatorResponse(e);
    }

    return ResponseEntity
        .status(500)
        .body(new Response("error", errorInstance.getMessage()));
  }

  private ResponseEntity<ResponseModel> getMainResponse(MainError error) {
    String status = error.getStatus();
    String message = error.getMessage();

    return ResponseEntity
        .status(500)
        .body(new Response(status, message));
  }

  private ResponseEntity<ResponseModel> getValidatorResponse(MethodArgumentNotValidException error) {
    InvalidField newError = new InvalidField(error.getFieldErrors());

    return ResponseEntity
        .status(400)
        .body(new Response(newError.getStatus(), newError.getMessage()));
  }
}
