package com.example.homeworkten.services;
import com.example.homeworkten.models.Person;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @Override
    public void sendEmail(Person person) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(person.getEmail());
            helper.setSubject("Best Buy Registration Completed");
            helper.setText("<h3>Dear, " + person.getFirstName() + "!</h3><br>" +
                    "Your registration in BestBuy shop was successful.</h3><br>" +
                    "Try you best shopping experience with BestBuy via link below:</h4></i> " +
                    "<a href=\"http://localhost:8080/login\" target=\"_blank\">Log in</a>", true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
