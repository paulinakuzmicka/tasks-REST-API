package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;


import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MailCreatorService mailCreatorService;


    @Test
    public void shouldSendDailyEmail() {

        //Given
        Mail mail = new Mail("test@test.com", "Test", "Test message");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        EmailTemplateSelector template = EmailTemplateSelector.SCHEDULED_EMAIL;

        //When
        simpleEmailService.send(mail, template);

        //Then

        //verify(mailCreatorService, times(1)).buildScheduledEmail(mail.getMessage());
    }

    @Test
    public void shouldSendTrelloCardEmail() {

        //Given
        Mail mail = new Mail("test@test.com", "cctest@test.com", "Test", "Test message");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setCc(mail.getToCc());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        EmailTemplateSelector template = EmailTemplateSelector.TRELLO_CARD_EMAIL;

        //When
        simpleEmailService.send(mail, template);

        //Then

    }
}