package com.martins.Agenda_Pro.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martins.Agenda_Pro.errors.LoginException;
import com.martins.Agenda_Pro.repository.login.table.User;
import com.martins.Agenda_Pro.security.JwtEncode;
import com.martins.Agenda_Pro.security.hash.PassworHashing;
import com.martins.Agenda_Pro.services.login.LoginRequestDTO;
import com.martins.Agenda_Pro.services.login.LoginService;
import com.martins.Agenda_Pro.services.login.LoginValidator;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class LoginController {

  private final Map<String, String> response = new HashMap<>();
  private final LoginService service;

  public LoginController(LoginService service) {
    this.service = service;
  }

  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> AuthenticateUser(@RequestBody LoginRequestDTO request,
      HttpServletRequest server) {
    try {
      // Função herdada da classe base para verificar rating limmits
      this.response.clear();

      // Valida se email e password foram recebidos
      LoginValidator.validate(request);

      User user = service.buscarPorEmail(request.getEmail());

      PassworHashing hashing = new PassworHashing();
      boolean res = hashing.verifyPassword(user.getPassword(), request.getPassword());
      if (!res) {
        throw new LoginException("Credenciais inválidas", null);
      }

      Map<String, String> claims = user.CreateJwtClaims();

      JwtEncode encode = new JwtEncode();

      String token = encode.generateToken(claims);
      this.response.put("status", "success");
      this.response.put("token", token);

      return ResponseEntity.ok(this.response);

    } catch (Exception e) {
      return ResponseEntity.status(500).body(Map.of(
          "status", "error",
          "message", "Erro interno"));
    }
  }

}
