package com.martins.Agenda_Pro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martins.Agenda_Pro.controller.entities.InsertClientResponse;
import com.martins.Agenda_Pro.errors.MainError;
import com.martins.Agenda_Pro.repository.clients.table.Client;
import com.martins.Agenda_Pro.responses.ErrorResponse;
import com.martins.Agenda_Pro.responses.ResponseModel;
import com.martins.Agenda_Pro.services.insert_client.InsertClientDTO;
import com.martins.Agenda_Pro.services.insert_client.InsertClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class InsertClient {

  private final InsertClientService service;

  public InsertClient(InsertClientService service) {
    this.service = service;
  }

  @PostMapping("/create/client")
  public ResponseEntity<ResponseModel> InsertClientController(@Valid @RequestBody InsertClientDTO request) {
    try {
      Client insert = service.salvar(request);

      return ResponseEntity
          .ok(new InsertClientResponse("sucesso", "Cliente adicionado com sucesso"));
    } catch (MainError e) {
      return ResponseEntity
          .status(e.getStatus())
          .body(new ErrorResponse("error", e.getMessage()));

    } catch (Exception e) {
      return ResponseEntity
          .status(500)
          .body(new ErrorResponse("error", e.getMessage()));
    }
  }
}
