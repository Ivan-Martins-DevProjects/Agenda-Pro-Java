package com.martins.Agenda_Pro.events.consumers;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.martins.Agenda_Pro.events.recorders.RecoverEmailRecorder;
import com.martins.Agenda_Pro.services.emailService.EmailService;

@Component
public class RecoverEmailListener {

  @Async
  @EventListener
  public void recoverEmail(RecoverEmailRecorder event) {
    EmailService sender = new EmailService();

    sender.sendLoginRecoverEmail(event.email());
  }
}
