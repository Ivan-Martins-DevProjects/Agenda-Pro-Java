package com.martins.Agenda_Pro.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martins.Agenda_Pro.repository.login.table.User;
import com.martins.Agenda_Pro.security.JwtEncode;
import com.martins.Agenda_Pro.services.login.LoginRequestDTO;
import com.martins.Agenda_Pro.services.login.LoginService;
import com.martins.Agenda_Pro.services.login.LoginValidator;

@RestController
@RequestMapping("/api")
public class LoginController {

  private final LoginService service;

  public LoginController(LoginService service) {
    this.service = service;
  }

  @PostMapping("/login")
  public String AuthenticateUser(@RequestBody LoginRequestDTO request) {

    // Valida se email e password foram recebidos
    LoginValidator.validate(request);

    User user = service.buscarPorEmail(request.getEmail());
    Map<String, String> claims = user.CreateJwtClaims();

    JwtEncode encode = new JwtEncode();
    String token = encode.generateToken(claims);

    return token;
  }
}
