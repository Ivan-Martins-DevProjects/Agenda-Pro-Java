package com.martins.Agenda_Pro.login.services.mock;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.martins.Agenda_Pro.errors.MainError;
import com.martins.Agenda_Pro.errors.UserNotFound;
import com.martins.Agenda_Pro.repository.login.LoginRepository;
import com.martins.Agenda_Pro.repository.login.table.User;
import com.martins.Agenda_Pro.services.login.LoginService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class LoginServiceTest {

  @Test
  void testBuscarPorEmailSucesso() {
    // Mock do repository
    LoginRepository mockRepository = mock(LoginRepository.class);

    // Mock do usuário
    User mockUser = new User();
    mockUser.setEmail("teste@ex.com");

    // Quando findByEmail for chamado, retorna Optional com o mockUser
    when(mockRepository.findByEmail("teste@ex.com")).thenReturn(Optional.of(mockUser));

    // Serviço real com repository mockado
    LoginService service = new LoginService(mockRepository);

    User user = service.buscarPorEmail("teste@ex.com");

    assertNotNull(user);
    assertEquals("teste@ex.com", user.getEmail());
  }

  @Test
  void testBuscarPorEmailNaoEncontrado() {
    // Mock do repository
    LoginRepository mockRepository = mock(LoginRepository.class);

    // Retorna Optional vazio
    when(mockRepository.findByEmail("naoexiste@ex.com")).thenReturn(Optional.empty());

    LoginService service = new LoginService(mockRepository);

    // Deve lançar RuntimeException
    UserNotFound exception = assertThrows(UserNotFound.class, () -> {
      service.buscarPorEmail("naoexiste@ex.com");
    });

    assertEquals("Usuário não encontrado", exception.getMessage());
  }
}
