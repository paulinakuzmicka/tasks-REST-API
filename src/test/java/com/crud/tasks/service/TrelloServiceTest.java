package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static com.crud.tasks.service.EmailTemplateSelector.TRELLO_CARD_EMAIL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void fetchTrelloBoardsEmptyListTest() {
        //Given
        when(trelloClient.getTrelloBoards()).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    public void createTrelloCardTest() {
        //Given
        TrelloBadgesDto trelloBadgesDto = new TrelloBadgesDto(5, new
                TrelloAttachmentsByTypeDto(new TrelloTrelloDto(3, 4)));

        CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto("1", "card", "com/org", trelloBadgesDto);
        TrelloCardDto card = new TrelloCardDto("card", "description", "pos", "1");

        when(trelloClient.createNewCard(card)).thenReturn(createdCard);
        when(adminConfig.getAdminMail()).thenReturn("mail@mail.com");

        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createTrelloCard(card);

        //Then
        assertEquals(createdCard.getId(), createdTrelloCardDto.getId());
        assertEquals(createdCard.getName(), createdTrelloCardDto.getName());
        assertEquals(createdCard.getShortUrl(), createdTrelloCardDto.getShortUrl());
        verify(emailService, times(1)).
                send(argThat(
                        new MailMatcher(
                                new Mail("mail@mail.com", "", ""))),
                        argThat(new EmailTemplateSelectorMatcher(EmailTemplateSelector.TRELLO_CARD_EMAIL)));
    }

    private class MailMatcher implements ArgumentMatcher<Mail> {
        private final Mail expected;

        public MailMatcher(Mail expected) {
            this.expected = expected;

        }

        @Override
        public boolean matches(Mail mail) {
            return mail.getMailTo().equals(expected.getMailTo());
        }
    }

    private class EmailTemplateSelectorMatcher implements ArgumentMatcher<EmailTemplateSelector>{

        private final EmailTemplateSelector selector;

        public EmailTemplateSelectorMatcher(EmailTemplateSelector selector) {
            this.selector = selector;
        }

        @Override
        public boolean matches(EmailTemplateSelector selectorMatcher) {
            return selectorMatcher.equals(selector);
        }
    }
}
