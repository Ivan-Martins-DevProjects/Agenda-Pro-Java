package com.martins.Agenda_Pro.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.martins.Agenda_Pro.controller.entities.LoginResponse;
import com.martins.Agenda_Pro.errors.LoginException;
import com.martins.Agenda_Pro.repository.login.table.User;
import com.martins.Agenda_Pro.responses.Response;
import com.martins.Agenda_Pro.responses.ResponseModel;
import com.martins.Agenda_Pro.security.JwtEncode;
import com.martins.Agenda_Pro.security.hash.PassworHashing;
import com.martins.Agenda_Pro.services.login.LoginRequestDTO;
import com.martins.Agenda_Pro.services.login.LoginService;

import jakarta.validation.Valid;

@RestController
public class LoginController extends AbstractController {

  private final LoginService service;
  private final PassworHashing hashing;

  public LoginController(LoginService service, PassworHashing hashing) {
    this.service = service;
    this.hashing = hashing;
  }

  @PostMapping("/login") // <- /api/login ->
  public ResponseEntity<ResponseModel> AuthenticateUser(@Valid @RequestBody LoginRequestDTO request) {
    try {
      User user = service.buscarPorEmail(request.getEmail());

      boolean res = hashing.verifyPassword(user.getPassword(),
          request.getPassword());
      if (!res) {
        throw new LoginException(400, "Credenciais inválidas");
      }

      Map<String, String> claims = user.CreateJwtClaims();

      JwtEncode encode = new JwtEncode();

      String token = encode.generateToken(claims);

      return ResponseEntity
          .ok(new LoginResponse("sucesso", 0, token));

    } catch (Exception e) {
      return createErrorResponse(e);
    }
  }

