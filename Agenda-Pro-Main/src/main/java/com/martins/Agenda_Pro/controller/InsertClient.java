package com.martins.Agenda_Pro.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InsertClient {

  @PostMapping("/create/client")
  public Map<String, String> InsertClientController() {

    return new HashMap<>();
  }
}
