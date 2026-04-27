package com.martins.Agenda_Pro.errors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;

public class InvalidField extends MainError {
  private final List<FieldError> errors;

  public InvalidField(List<FieldError> errorList) {
    super("error", 400, "Campos inválidos");
    this.errors = errorList;
  }

  public Map<String, String> getResponse() {
    Map<String, String> response = new HashMap<>();
    response.put("Status", getMessage());

    for (FieldError error : errors) {
      String field = error.getField();
      String message = error.getDefaultMessage();

      response.put(field, message);
    }

    return response;
  }
}
