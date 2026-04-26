package com.martins.Agenda_Pro.security.hash;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

import com.martins.Agenda_Pro.errors.PasswordHashException;

import de.mkammerer.argon2.Argon2;

@Service
public class PassworHashing {
  private final Argon2 argonType;

  public PassworHashing(Argon2 argonType) {
    this.argonType = argonType;
  }

  public boolean verifyPassword(String hash, String password) {
    byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
    Argon2 argon2 = this.argonType;

    try {
      return argon2.verify(hash, passwordBytes);
    } catch (Exception e) {
      throw new PasswordHashException(500, "Erro ao comparar senha");
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
      throw new PasswordHashException(500, "Erro ao gerar hash da senha");
    } finally {
      if (passwordBytes != null) {
        argon2.wipeArray(passwordBytes);
      }
    }
  }
}
