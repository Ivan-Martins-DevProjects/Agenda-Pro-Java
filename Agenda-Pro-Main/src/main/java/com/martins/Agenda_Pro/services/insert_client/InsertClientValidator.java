package com.martins.Agenda_Pro.services.insert_client;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.context.annotation.Bean;

public class InsertClientValidator extends ClientFieldsValidator {

  @Bean
  public void validate(InsertClientDTO insert) {
    validateField(insert.getName(), "name");
    validateField(insert.getPhone(), "phone");
    validateField(insert.getEmail(), "email");
    validateField(insert.getOwner(), "owner");
    validateField(insert.getObs(), "obs");
  }

  private void validateField(String value, String fieldName) {
    if (value == null || value.isEmpty()) {
      throw new IllegalArgumentException("O valor de '" + fieldName + "' não pode ser nulo");
    }

    switch (fieldName) {
      case "phone":
        checkPhone(value);
        break;

      case "name":
        checkName(value);
        break;

      case "email":
        checkEmail(value);
        break;

      default:
        break;
    }
  }

}
