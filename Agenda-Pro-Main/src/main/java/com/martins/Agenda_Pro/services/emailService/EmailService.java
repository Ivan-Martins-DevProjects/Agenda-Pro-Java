package com.martins.Agenda_Pro.services.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  @Value("${app.email.sender}")
  private String sender;

  @Value("${app.email.recovery.subject}")
  private String subject;

  // Todo: Gerar link de recuperação de email
  public void sendLoginRecoverEmail(String receiver) {
    String content = """
        Olá,

        Recebemos uma solicitação para redefinir a senha da sua conta na nossa plataforma.

        Se foi você quem fez essa solicitação, clique no link abaixo para criar uma nova senha:

        [Inserir link de recuperação]

        Caso você não tenha solicitado a recuperação de senha, recomendamos que ignore este e-mail. Sua conta continuará segura.
        Se precisar de ajuda, entre em contato com nosso suporte.

        Atenciosamente,
        Equipe de Suporte

        """;

    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(sender);
    message.setTo(receiver);
    message.setSubject(subject);
    message.setText(content);

    mailSender.send(message);
  }
}
