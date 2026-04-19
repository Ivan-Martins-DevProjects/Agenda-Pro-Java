package com.martins.Agenda_Pro.login.controllers.mock;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Map;

import com.martins.Agenda_Pro.controller.LoginController;
import com.martins.Agenda_Pro.repository.login.table.User;
import com.martins.Agenda_Pro.services.login.LoginRequestDTO;
import com.martins.Agenda_Pro.services.login.LoginService;

import jakarta.servlet.http.HttpServletRequest;

public class LoginControllerMockTest {

  @Test
  void AuthenticateUserTest() {
    LoginService mockService = mock(LoginService.class);

    // Mock do usuário
    User mockUser = mock(User.class);
    when(mockUser.CreateJwtClaims()).thenReturn(Map.of("id", "1", "email", "teste@ex.com"));

    // Quando buscarPorEmail for chamado, retornar mockUser
    when(mockService.buscarPorEmail("teste@ex.com")).thenReturn(mockUser);

    // Controler real usando serviço mockado
    LoginController controller = new LoginController(mockService);

    // DTO de teste
    LoginRequestDTO requestDTO = new LoginRequestDTO();
    requestDTO.setEmail("teste@ex.com");
    requestDTO.setPassword("123456");

    // Chama o método
    HttpServletRequest request = mock(HttpServletRequest.class);
    ResponseEntity<Map<String, String>> token = controller.AuthenticateUser(requestDTO, request);

    // Validação simples
    assertNotNull(token);
  }
}
