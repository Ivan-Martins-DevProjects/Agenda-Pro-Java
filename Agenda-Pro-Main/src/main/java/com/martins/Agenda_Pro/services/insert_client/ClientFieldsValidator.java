package com.martins.Agenda_Pro.services.insert_client;

public class ClientFieldsValidator {

  protected void checkPhone(String value) {
    if (value.length() != 11) {
      throw new IllegalArgumentException("Número de telefone muito curto ou extenso");
    }

    if (!value.matches("^\\d+$")) {
      throw new IllegalArgumentException("O campo telefone deve conter apenas números");
    }
  }

  protected void checkName(String value) {
    String[] splitName = value.split(" ");
    if (value.matches(".*\\d.*")) {
      throw new IllegalArgumentException("Não é permitido a utilização de números no campo nome");
    }

    if (splitName.length < 2) {
      throw new IllegalArgumentException("Nome inválido, é necessário haver nome e sobrenome");
    }
  }

  protected void checkEmail(String value) {
    if (!value.contains("@") || !value.contains(".")) {
      throw new IllegalArgumentException("Endereço de email inválido");
    }
  }
}
