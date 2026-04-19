package com.martins.Agenda_Pro.login.controllers.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class LoginControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

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
  void testRateLimitterBloqueiaAposLimit() throws Exception {
    String json = """
            {
                "email": "admin@email.com",
                "password": "admin"
            }
        """;

    for (int i = 0; i < 2; i++) {
      mockMvc.perform(post("/api/login")
          .contentType("application/json")
          .content(json))
          .andExpect(status().isOk());
    }

    mockMvc.perform(post("/api/login")
        .contentType("application/json")
        .content(json))
        .andExpect(status().isTooManyRequests());
  }
}
