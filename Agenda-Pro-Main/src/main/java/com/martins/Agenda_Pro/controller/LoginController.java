package com.martins.Agenda_Pro.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.martins.Agenda_Pro.controller.entities.LoginResponse;
import com.martins.Agenda_Pro.errors.LoginException;
import com.martins.Agenda_Pro.events.publishers.emailRecovery.RecoverEmail;
import com.martins.Agenda_Pro.repository.login.table.User;
import com.martins.Agenda_Pro.responses.Response;
import com.martins.Agenda_Pro.responses.ResponseModel;
import com.martins.Agenda_Pro.security.JwtEncode;
import com.martins.Agenda_Pro.security.hash.PassworHashing;
import com.martins.Agenda_Pro.services.login.LoginRequestDTO;
import com.martins.Agenda_Pro.services.login.LoginService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Login", description = "Endpoint para login dos usuários")
public class LoginController extends AbstractController {

  private final RecoverEmail emailService;
  private final LoginService service;
  private final PassworHashing hashing;

  public LoginController(
      LoginService service,
      PassworHashing hashing,
      RecoverEmail emailService) {

    this.service = service;
    this.hashing = hashing;
    this.emailService = emailService;
  }

  @Operation(summary = "Realiza os logins dos usuários", description = "Retorna um JWT token com informações do usuários, com expiração de 1 hora")
  @PostMapping("/login") // <- /api/login ->
  public ResponseEntity<ResponseModel> AuthenticateUser(@Valid @RequestBody LoginRequestDTO request) {
    try {
      User user = service.buscarPorEmail(request.getEmail());

      boolean res = hashing.verifyPassword(user.getPassword(),
          request.getPassword());
      if (!res) {
        throw new LoginException("error", 400, "Credenciais inválidas");
      }

      Map<String, String> claims = user.CreateJwtClaims();

      JwtEncode encode = new JwtEncode();

      String token = encode.generateToken(claims);

      return ResponseEntity
          .ok(new LoginResponse("sucesso", token));

    } catch (Exception e) {
      return createErrorResponse(e);
    }
  }

  @Operation(summary = "Endpoint para recuperação de conta", description = "Envia um email caso o email seja encontrado")
  @PostMapping("/login/recover-password")
  public ResponseEntity<ResponseModel> RecoverPassword(@Valid @RequestBody LoginRequestDTO request) {
    try {
      User user = service.buscarPorEmail(request.getEmail());
      emailService.emailRecovery(user.getEmail());

      return ResponseEntity
          .ok(new Response(
              "sucesso",
              "Se seu email foi encontrado você receberá um link de recuperação na sua caixa de entrada"));

    } catch (Exception e) {
      return createErrorResponse(e);
    }
  }

}
