package com.martins.Agenda_Pro.services.insert_client;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class InsertClientDTO {

  @NotBlank(message = "ClientId é obrigatório")
  @Size(max = 250, message = "ClientId inválido")
  private String clientId;

  @NotBlank(message = "Nome é obrigatório")
  @Size(min = 4, max = 50, message = "Nome deve ter entre 4 e 50 caracteres")
  private String nome;

  @Email(message = "Email inválido")
  @Size(min = 10, max = 70, message = "Email deve ter entre 10 e 70 caracteres")
  private String email;

  private Status status;

  @NotBlank(message = "Telefone é obrigatório")
  @Size(min = 11, max = 13, message = "Telefone deve ter entre 11 e 13 caracteres")
  private String telefone;

  @Max(value = 100, message = "Limite de 100 visitas alcançado")
  @Min(value = 0, message = "Limite mínimo alcançado")
  private int visitas;

  @Max(value = 2147483647, message = "Limite de gasto atingido")
  @Min(value = 0, message = "Limite mínimo alcançado")
  private int gasto;

  @Size(max = 255, message = "Valor máximo de caracteres na observação atingido")
  private String obs;

  @Size(max = 100, message = "Nome do responsável muito extenso")
  private String resp_name;

  @Size(max = 255, message = "Bussines ID muito extenso")
  private String bussines_id;

  @CPF(message = "CPF inválido")
  private String cpf;

  public enum Status {
    ATIVO,
    INATIVO,
    PENDENTE
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public int getVisitas() {
    return visitas;
  }

  public void setVisitas(int visitas) {
    this.visitas = visitas;
  }

  public int getGasto() {
    return gasto;
  }

  public void setGasto(int gasto) {
    this.gasto = gasto;
  }

  public String getObs() {
    return obs;
  }

  public void setObs(String obs) {
    this.obs = obs;
  }

  public String getResp_name() {
    return resp_name;
  }

  public void setResp_name(String resp_name) {
    this.resp_name = resp_name;
  }

  public String getBussines_id() {
    return bussines_id;
  }

  public void setBussines_id(String bussines_id) {
    this.bussines_id = bussines_id;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
}
