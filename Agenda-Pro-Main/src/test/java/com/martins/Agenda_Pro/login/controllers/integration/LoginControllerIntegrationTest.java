package com.martins.Agenda_Pro.login.controllers.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class LoginControllerIntegrationTest {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext context;

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders
        .webAppContextSetup(context)
        .build();
  }

  @Test
  void testAuthenticateUser() throws Exception {
    String json = """
            {
                "email": "admin@email.com",
                "password": "admin"
            }
        """;

    mockMvc.perform(post("/api/login")
        .contentType("application/json")
        .content(json))
        .andExpect(status().isOk()); // espera retorno 200
  }

  @Test
  void testUsuarioNaoEncontrado() throws Exception {
    String json = """
              {
                  "email": "usuario@invalido.com",
                  "password": "admin"
              }
        """;

    mockMvc.perform(post("/api/login")
        .contentType("application/json")
        .content(json))
        .andExpect(status().isNotFound());
  }
}
