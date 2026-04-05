package com.martins.Agenda_Pro.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martins.Agenda_Pro.services.login.LoginRequestDTO;
import com.martins.Agenda_Pro.services.login.LoginValidator;

@RestController
@RequestMapping("/api")
public class LoginController {

  @PostMapping("/login")
  public String AuthenticateUser(@RequestBody LoginRequestDTO request) {

    // Valida se username e password foram recebidos
    LoginValidator.validate(request);

    return "";
  }
}
