package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {
//    @InjectMocks
//    private EmailScheduler emailScheduler;
//
//    @Mock
//    private SimpleEmailService simpleEmailService;
//
//    @Mock
//    private AdminConfig adminConfig;
//
//    @Test
//    public void sendInformationEmailTest() {
//        //Given
//        when(adminConfig.getAdminMail()).thenReturn("mail@mail.com");
//
//        //When
//        emailScheduler.sendInformationEmail();
//
//        //Then
//        verify(simpleEmailService, times(1)).send(any());
//    }
}
