package com.martins.Agenda_Pro.events.publishers.emailRecovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.martins.Agenda_Pro.events.recorders.RecoverEmailRecorder;

@Service
public class RecoverEmail {

  @Autowired
  private ApplicationEventPublisher eventPublisher;

  public void emailRecovery(String email) {
    eventPublisher.publishEvent(
        new RecoverEmailRecorder(email));
  }
}
