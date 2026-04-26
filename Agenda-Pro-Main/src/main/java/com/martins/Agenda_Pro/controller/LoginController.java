package com.martins.Agenda_Pro.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martins.Agenda_Pro.controller.entities.LoginResponse;
import com.martins.Agenda_Pro.errors.LoginException;
import com.martins.Agenda_Pro.repository.login.table.User;
import com.martins.Agenda_Pro.responses.ErrorResponse;
import com.martins.Agenda_Pro.responses.ResponseModel;
import com.martins.Agenda_Pro.security.JwtEncode;
import com.martins.Agenda_Pro.security.hash.PassworHashing;
import com.martins.Agenda_Pro.services.login.LoginRequestDTO;
import com.martins.Agenda_Pro.services.login.LoginService;
import com.martins.Agenda_Pro.services.login.LoginValidator;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class LoginController {

  private final LoginService service;
  private final PassworHashing hashing;

  public LoginController(LoginService service, PassworHashing hashing) {
    this.service = service;
    this.hashing = hashing;
  }

  @PostMapping("/login")
  public ResponseEntity<ResponseModel> AuthenticateUser(@RequestBody LoginRequestDTO request,
      HttpServletRequest server) {
    try {
      // Valida se email e password foram recebidos
      LoginValidator.validate(request);

      User user = service.buscarPorEmail(request.getEmail());

      boolean res = hashing.verifyPassword(user.getPassword(),
          request.getPassword());
      if (!res) {
        throw new LoginException("Credenciais inválidas", null);
      }

      Map<String, String> claims = user.CreateJwtClaims();

      JwtEncode encode = new JwtEncode();

      String token = encode.generateToken(claims);

      LoginResponse response = new LoginResponse("Sucesso", token);
      return ResponseEntity.ok(response);

    } catch (Exception e) {
      ErrorResponse response = new ErrorResponse("error", e.getMessage());
      return ResponseEntity.status(500).body(response);
    }
  }

}
