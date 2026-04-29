package com.martins.Agenda_Pro.services.login;

import org.springframework.stereotype.Service;

import com.martins.Agenda_Pro.errors.UserNotFound;
import com.martins.Agenda_Pro.repository.login.LoginRepository;
import com.martins.Agenda_Pro.repository.login.table.User;

@Service
public class LoginService {

  // Variavel definida pela interface herdada do JPA
  private final LoginRepository repository;

  public LoginService(LoginRepository repository) {
    this.repository = repository;
  }

  // Essa função é criada pelo próprio JPA, usando essa sintaxe em relação a
  // propriedade acessada.
  // no caso User, ele executa a query automaticamente
  public User buscarPorEmail(String email) {
    return repository.findByEmail(email)
        .orElseThrow(() -> new UserNotFound("error", 404, "Usuário não encontrado"));
  }
}
