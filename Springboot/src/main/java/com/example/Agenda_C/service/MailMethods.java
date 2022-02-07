package com.example.Agenda_C.service;

import com.example.Agenda_C.domain.NotifEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

@Slf4j
public class MailMethods {
    public void sendMail(NotifEmail notificationEmail, String FROM, JavaMailSender mailSender) throws Exception {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(FROM);
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody());
        };

        try {
            mailSender.send(messagePreparator);
            log.info("Activation email has been sent");
        } catch (Exception e) {
            // TODO: handle exception
            log.error("Exception Occured when sending email | "+e);
            throw new Exception("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        }
    }
}
