package com.martins.Agenda_Pro.repository.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.martins.Agenda_Pro.repository.login.table.User;

public interface LoginRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
