package ua.golovchenko.artem.minimessage.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class UserAccountTest {

    @Autowired
    UserAccount userAccount;


    @Test
    public void testSetIdThenGetId() throws Exception {
        userAccount.setId(10L);

        assertNotNull(userAccount.getId());
        assertEquals(Long.valueOf(10L),userAccount.getId());
    }


    @Test
    public void testSetUsernameThenGetUsername() throws Exception {
        userAccount.setUsername("UserLogin");

        assertNotNull(userAccount.getUsername());
        assertEquals("UserLogin", userAccount.getUsername());
    }


    @Test
    public void testSetPasswordThenGetPassword() throws Exception {
        userAccount.setPassword("UserPa$$W0rD");

        assertNotNull(userAccount.getPassword());
        assertEquals("UserPa$$W0rD", userAccount.getPassword());
    }


/*    @Test
    public void testSetMessagesThenGetMessages() throws Exception {

    }*/


    @Test
    public void testGetCreated() throws Exception {
        Date accountCreated = new Date();

        userAccount.setCreated(accountCreated);

        assertNotNull(userAccount.getCreated());
        assertEquals(accountCreated, userAccount.getCreated());
    }

}