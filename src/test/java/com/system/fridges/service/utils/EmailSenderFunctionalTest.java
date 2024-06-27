package com.system.fridges.service.utils;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailSenderFunctionalTest {

    @Test
    public void testSendEmail() throws MessagingException {
        EmailSender emailSender = new EmailSender();
        String to = "pasakane990@gmail.com";
        String body = "Test email body";

        emailSender.sendEmail(to, body);
    }
}
