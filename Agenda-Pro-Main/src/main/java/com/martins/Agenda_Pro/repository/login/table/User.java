package com.martins.Agenda_Pro.repository.login.table;

import java.util.List;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;
  private String email;
  private String password;
  private String role;
  private String instance;
  private String name;
  private String bussinessId;

  public Map<String, String> CreateJwtClaims() {
    Map<String, String> claims = new HashMap<>();
    List<String> fields = List.of("id", "email", "password", "role", "instance", "name", "bussinessId");

    for (String fieldName : fields) {
      try {
        Field field = this.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        Object value = field.get(this);
        claims.put(fieldName, value != null ? value.toString() : null);
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Erro ao gerar claims");
      }
    }

    return claims;
  }

  public User() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getInstance() {
    return instance;
  }

  public void setInstance(String instance) {
    this.instance = instance;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBussinessId() {
    return bussinessId;
  }

  public void setBussinessId(String bussinessId) {
    this.bussinessId = bussinessId;
  }
}
