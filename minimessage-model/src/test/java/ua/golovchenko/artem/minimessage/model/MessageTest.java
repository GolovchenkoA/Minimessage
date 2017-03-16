package ua.golovchenko.artem.minimessage.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTest {

    Message message;

    @Before
    public void init(){
        message = new Message();
    }

    @Test
    public void testSetId() throws Exception {

        assertNull(message.getId());

        message.setId(1L);
        Long actualId = message.getId();

        assertNotNull(actualId);
        assertEquals(Long.valueOf(1L),actualId);
    }

    @Test
    public void testSetAccount() throws Exception {

        assertNull(message.getAccount());

        UserAccount account = new UserAccount();
        account.setUsername("AccountName");
        message.setAccount(account);
        String accountName = message.getAccount().getUsername();

        assertNotNull(message.getAccount());
        assertEquals("AccountName", accountName);
    }

    @Test
    public void testSetText() throws Exception {

        String text = "New Message Test";

        assertNull(message.getText());

        message.setText(text);

        assertNotNull(message.getText());
        assertEquals(text,message.getText());

    }

    @Test
    public void testSetCreated() throws Exception {

        java.util.Date messageCreated = new java.util.Date();

        message.setCreated(messageCreated);

        assertNotNull(messageCreated);
        assertEquals(messageCreated, message.getCreated());
    }
}