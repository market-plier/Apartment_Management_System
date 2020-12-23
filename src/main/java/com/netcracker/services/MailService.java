package com.netcracker.services;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Log4j
@Service
public class MailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendMessage(String to, String subject, String text) throws MailException, NullPointerException {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@baeldung.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        } catch (NullPointerException | MailException e) {
            log.error("MailService method sendMessage: " + e.getMessage(), e);
            throw e;
        }
    }

    public void sendMessageWithAttachment(String to, String subject, String text, String filename, ByteArrayInputStream stream)
            throws MessagingException, IOException, NullPointerException {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("noreply@baeldung.com");
            helper.setTo(to);
            helper.setSubject(subject);

            //construct the text body part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(text);

            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(stream, "application/pdf");
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.setDataHandler(new DataHandler(dataSource));
            attachment.setFileName(filename);

            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textPart);
            mimeMultipart.addBodyPart(attachment);

            message.setContent(mimeMultipart);
            emailSender.send(message);
        } catch (NullPointerException | MessagingException | IOException e) {
            log.error("MailService method sendMessageWithAttachment: " + e.getMessage(), e);
            throw e;
        }
    }
}
