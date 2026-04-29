package com.martins.Agenda_Pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AgendaProApplication {

  public static void main(String[] args) {
    SpringApplication.run(AgendaProApplication.class, args);
  }

}
