package ua.golovchenko.artem.minimessage.mvc;

import org.junit.Test;
import ua.golovchenko.artem.minimessage.model.Message;
import ua.golovchenko.artem.minimessage.service.MinimessagesService;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ua.golovchenko.artem.minimessage.mvc.HomeController.*;

public class HomeControllerTest {

    @Test
    public void shouldDisplayRecentSpittles() {
        List<Message> expectedMessages = asList(new Message(),new Message(), new Message());

        MinimessagesService minimessagesService = mock(MinimessagesService.class);

        when(minimessagesService.getRecentMessages(DEFAULT_MESSAGES_PER_PAGE)).thenReturn(expectedMessages);

        HomeController homeController = new HomeController(minimessagesService);

        HashMap<String,Object> model = new HashMap<String, Object>();
        String viewName = homeController.showHomePage(model);

        assertEquals("home",viewName);
        assertSame(expectedMessages,model.get("messages"));
        verify(minimessagesService).getRecentMessages(DEFAULT_MESSAGES_PER_PAGE);



    }

}