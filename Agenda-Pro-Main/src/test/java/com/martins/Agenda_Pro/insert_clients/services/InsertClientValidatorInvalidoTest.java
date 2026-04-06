package com.martins.Agenda_Pro.insert_clients.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.martins.Agenda_Pro.services.insert_client.InsertClientDTO;
import com.martins.Agenda_Pro.services.insert_client.InsertClientValidator;

public class InsertClientValidatorInvalidoTest {

  private final InsertClientDTO request = new InsertClientDTO();

  @Test
  void deveRetornarNomeInválidoPorNumerosNele() {
    request.setEmail("email@email.com");
    request.setObs("teste");
    request.setName("ivan 123");
    request.setOwner("ivan");
    request.setPhone("1182731873");

    InsertClientValidator validator = new InsertClientValidator();

    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
      validator.validate(request);
    });

    assertEquals("Não é permitido a utilização de números no campo nome", ex.getMessage());
  }

  @Test
  void deveRetornarNomeInválidoPorNaoTerSobrenome() {
    request.setEmail("email@email.com");
    request.setObs("teste");
    request.setName("ivan");
    request.setOwner("ivan");
    request.setPhone("1182731873");

    InsertClientValidator validator = new InsertClientValidator();

    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
      validator.validate(request);
    });

    assertEquals("Nome inválido, é necessário haver nome e sobrenome", ex.getMessage());
  }

  @Test
  void deveRetornarTelefoneCurto() {
    request.setEmail("email@email.com");
    request.setObs("teste");
    request.setName("ivan martins");
    request.setOwner("ivan");
    request.setPhone("11827318");

    InsertClientValidator validator = new InsertClientValidator();

    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
      validator.validate(request);
    });

    assertEquals("Número de telefone muito curto ou extenso", ex.getMessage());
  }

  @Test
  void deveRetornarTelefoneLongo() {
    request.setEmail("email@email.com");
    request.setObs("teste");
    request.setName("ivan martins");
    request.setOwner("ivan");
    request.setPhone("118273181231");

    InsertClientValidator validator = new InsertClientValidator();

    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
      validator.validate(request);
    });

    assertEquals("Número de telefone muito curto ou extenso", ex.getMessage());
  }

  @Test
  void deveRetornarTelefoneComCaracterInvalido() {
    request.setEmail("email@email.com");
    request.setObs("teste");
    request.setName("ivan martins");
    request.setOwner("ivan");
    request.setPhone("1182731@#!&");

    InsertClientValidator validator = new InsertClientValidator();

    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
      validator.validate(request);
    });

    assertEquals("O campo telefone deve conter apenas números", ex.getMessage());
  }

  @Test
  void deveRetornarEmailInvalido() {
    request.setEmail("email@emailcom");
    request.setObs("teste");
    request.setName("ivan martins");
    request.setOwner("ivan");
    request.setPhone("11827318731");

    InsertClientValidator validator = new InsertClientValidator();

    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
      validator.validate(request);
    });

    assertEquals("Endereço de email inválido", ex.getMessage());
  }
}
