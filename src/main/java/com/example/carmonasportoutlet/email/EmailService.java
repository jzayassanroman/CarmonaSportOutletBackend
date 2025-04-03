package com.example.carmonasportoutlet.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class EmailService {
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom random = new SecureRandom();

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String toEmail, String verificationToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Verificación de cuenta");
        message.setText("Tu código de verificación es: " + verificationToken);

        mailSender.send(message);
    }

    private String generateVerificationCode() {
        StringBuilder code = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            code.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
        }
        return code.toString();
    }
}

