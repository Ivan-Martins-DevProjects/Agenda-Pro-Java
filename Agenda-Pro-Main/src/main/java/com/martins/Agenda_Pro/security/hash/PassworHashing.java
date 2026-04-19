package com.martins.Agenda_Pro.security.hash;

import java.nio.charset.StandardCharsets;

import com.martins.Agenda_Pro.errors.PasswordHashException;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PassworHashing {
  Argon2 argonType = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

  public boolean verifyPassword(String hash, String password) {
    byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
    Argon2 argon2 = this.argonType;

    try {
      return argon2.verify(hash, passwordBytes);
    } catch (Exception e) {
      throw new PasswordHashException("Erro ao comparar senha", e);
    } finally {
      if (passwordBytes != null) {
        argon2.wipeArray(passwordBytes);
      }
    }
  }

  public String hashPassword(String password) {
    byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
    Argon2 argon2 = this.argonType;

    try {
      return argon2.hash(3, 65536, 1, passwordBytes);
    } catch (Exception e) {
      throw new PasswordHashException("Erro ao gerar hash da senha", e);
    } finally {
      if (passwordBytes != null) {
        argon2.wipeArray(passwordBytes);
      }
    }
  }
}
