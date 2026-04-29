package com.martins.Agenda_Pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.martins.Agenda_Pro.errors.ErrorHandler;
import com.martins.Agenda_Pro.responses.ResponseModel;

@RequestMapping("/api")
public class AbstractController {

  @Autowired
  private ErrorHandler errorHandler;

  protected ResponseEntity<ResponseModel> createErrorResponse(Exception e) {
    return errorHandler.getResponse(e);
  }
}
