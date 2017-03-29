package ua.golovchenko.artem.minimessage.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class UserAccountTest {

    @Autowired
    UserAccount userAccount;


    @Before
    public void setUp(){
        userAccount = new UserAccount();
    }


    @Test
    public void testSetIdThenGetIdMustBeEquals() throws Exception {
        userAccount.setId(10L);

        assertNotNull(userAccount.getId());
        assertEquals(Long.valueOf(10L),userAccount.getId());
    }


    @Test
    public void testSetUsernameThenGetUsernameMustBeEquals() throws Exception {
        userAccount.setUsername("UserLogin");

        assertNotNull(userAccount.getUsername());
        assertEquals("UserLogin", userAccount.getUsername());
    }


    @Test
    public void testSetPasswordThenGetPasswordMustBeEquals() throws Exception {
        userAccount.setPassword("UserPa$$W0rD");

        assertNotNull(userAccount.getPassword());
        assertEquals("UserPa$$W0rD", userAccount.getPassword());
    }


    @Test
    public void testSetMessagesThenGetMessagesMustBeEquals() throws Exception {

        userAccount.setUsername("AccountUserName");
        userAccount.setPassword("AccountUserPassword");
        userAccount.setCreated(new Date());

        //create messages
        Set<Message> userMessages = new HashSet<>();
        String message1_Text = "Message 1";
        Message message = new Message(userAccount,message1_Text,new Date());

        // add message to messages
        userMessages.add(message);
        userAccount.setMessages(userMessages);

        Set<Message>allUserMessages = userAccount.getMessages();

        assertNotNull(allUserMessages);
/*        System.out.println("All messages: ");
        for(Message m : userMessages){
            System.out.println(m);
        }*/
        assertThat(allUserMessages.size(),is(1));
        assertTrue(userMessages.contains(message));
    }


    @Test
    public void testGetCreated() throws Exception {
        Date accountCreated = new Date();

        userAccount.setCreated(accountCreated);

        assertNotNull(userAccount.getCreated());
        assertEquals(accountCreated, userAccount.getCreated());
    }

}