package com.martins.Agenda_Pro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InsertClient {

  @PostMapping
  public String helloWorld() {
    return "Hello World!";
  }
}
