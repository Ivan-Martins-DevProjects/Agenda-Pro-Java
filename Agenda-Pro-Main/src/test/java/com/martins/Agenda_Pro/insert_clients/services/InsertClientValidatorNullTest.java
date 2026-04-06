package com.martins.Agenda_Pro.insert_clients.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.martins.Agenda_Pro.services.insert_client.InsertClientDTO;
import com.martins.Agenda_Pro.services.insert_client.InsertClientValidator;

public class InsertClientValidatorNullTest {
  private final InsertClientDTO request = new InsertClientDTO();

  @Test
  void deveRetornarNomeNulo() {
    request.setEmail("email@email.com");
    request.setObs("teste");
    request.setOwner("ivan");
    request.setPhone("11937467281");

    InsertClientValidator validator = new InsertClientValidator();

    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
      validator.validate(request);
    });

    assertEquals("O valor de 'name' não pode ser nulo", ex.getMessage());
  }

  @Test
  void deveRetornarEmailNulo() {
    request.setName("ivan martins");
    request.setObs("teste");
    request.setOwner("ivan");
    request.setPhone("11937467281");

    InsertClientValidator validator = new InsertClientValidator();

    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
      validator.validate(request);
    });

    assertEquals("O valor de 'email' não pode ser nulo", ex.getMessage());
  }

  @Test
  void deveRetornarPhoneNulo() {
    request.setName("ivan martins");
    request.setEmail("email@email.com");
    request.setObs("teste");
    request.setOwner("ivan");

    InsertClientValidator validator = new InsertClientValidator();

    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
      validator.validate(request);
    });

    assertEquals("O valor de 'phone' não pode ser nulo", ex.getMessage());
  }

  @Test
  void deveRetornarObsNulo() {
    request.setName("ivan martins");
    request.setEmail("email@email.com");
    request.setPhone("11937467281");
    request.setOwner("ivan");

    InsertClientValidator validator = new InsertClientValidator();

    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
      validator.validate(request);
    });

    assertEquals("O valor de 'obs' não pode ser nulo", ex.getMessage());
  }

  @Test
  void deveRetornarOwnerNulo() {
    request.setName("ivan martins");
    request.setEmail("email@email.com");
    request.setPhone("11937467281");
    request.setObs("teste");

    InsertClientValidator validator = new InsertClientValidator();

    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
      validator.validate(request);
    });

    assertEquals("O valor de 'owner' não pode ser nulo", ex.getMessage());
  }
}
