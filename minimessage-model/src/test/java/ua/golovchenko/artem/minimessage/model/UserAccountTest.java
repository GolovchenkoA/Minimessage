package ua.golovchenko.artem.minimessage.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class UserAccountTest {

    @Autowired
    UserAccount userAccount;
    private Set<UserAccount> accounts;


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

        Set<Message> allUserMessages = userAccount.getMessages();

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


    @Test
    public void testSetAccountRolesThenGetAccountRolesMustBeEquals(){
        List<AccountRole> roles = new ArrayList<>();
        roles.add(new AccountRoleImpl("ROLE_USER"));


    }

    @Test
    public void testAccountsWithSameRolesAndMessagesShouldBeEquals(){

        Date created = new Date();
        // Account 1
        UserAccount account = getNewUserAccountWithRoleUser();
        Set<Message> messages = new HashSet<>();
        // Account 2
        UserAccount account2 = getNewUserAccountWithRoleUser();
        Set<Message> messages2 = new HashSet<>();



        //add messages to account 1
        messages.add(new Message(account,"text",created));
        account.setMessages(messages);
        //add messages to account 1
        messages2.add(new Message(account,"text",created));
        account2.setMessages(messages2);


        assertTrue("Compare accounts", account.equals(account2));
        assertTrue("Compare accounts", account2.equals(account));

    }



     @Test
    public void testAccountsWithDifferentRolesShouldNotBeEquals(){

        Date created = new Date();
        // Account 1
        UserAccount user = getNewUserAccountWithRoleUser();
        // Account 2
        UserAccount admin = getNewUserAccountWithRoleAdmin();

        assertFalse("Compare accounts", user.equals(admin));
        assertFalse("Compare accounts", admin.equals(user));

    }





     @Test
    public void testAccountsWithDifferentMessagesShouldNotBeEquals(){

        Date created = new Date();
         // Account 1
        UserAccount account = getNewUserAccountWithRoleUser();
        Set<Message> messages = new HashSet<>();
        // Account 2
        Set<Message> messages2 = new HashSet<>();
        UserAccount account2 = getNewUserAccountWithRoleUser();


        //add messages to account 1
        messages.add(new Message(account,"text",created));
        account.setMessages(messages);
        //add messages to account 1
        messages2.add(new Message(account,"text2",created));
        account2.setMessages(messages2);


        assertFalse("Compare accounts", account.equals(account2));
        assertFalse("Compare accounts", account2.equals(account));
    }

    @Test
    public void testEnabled(){
        userAccount.setEnabled(true);
        assertTrue(userAccount.isEnabled());

        userAccount.setEnabled(false);
        assertFalse(userAccount.isEnabled());
    }


    @Test
    public void testSetPublishersThanGetPublishersShouldBeEquals() {

        UserAccount account = getNewUserAccountWithRoleUser();
        Set<UserAccount> Publishers = getSetOfAccounts();

        account.setPublishers(Publishers);

        assertEquals("Account Publishers", getSetOfAccounts(), account.getPublishers());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddPublisherWithoutLoginShouldThowException(){
        UserAccount account = getNewUserAccountWithRoleUser();
        account.addPublisher(new UserAccount());
    }

        @Test(expected = IllegalArgumentException.class)
    public void testRemovePublisherWithoutLoginShouldThowException(){
        UserAccount account = getNewUserAccountWithRoleUser();
        account.removePublisher(new UserAccount());
    }


    @Test
    public void testAddPublisherThenRemovePublisher(){
        UserAccount Publisher = new UserAccount("Login","Password", new Date());
        UserAccount account = getNewUserAccountWithRoleUser();
        account.addPublisher(Publisher);

        assertEquals("Publishers count",1,account.getPublishers().size());

        account.removePublisher(Publisher);

        assertEquals("Publishers count should be zero",0,account.getPublishers().size());

    }



    // Additional methods
    private UserAccount getNewUserAccountWithRoleUser(){
        Date created = new Date();

        UserAccount account = new UserAccount("name","password", created);
        AccountRoleImpl role = new AccountRoleImpl("user");
        account.addRole(role);

        return  account;
    }

    private UserAccount getNewUserAccountWithRoleAdmin(){
        Date created = new Date();

        UserAccount account = new UserAccount("name","password", created);
        AccountRoleImpl role = new AccountRoleImpl("admin");
        account.addRole(role);

        return  account;
    }


    private Set<UserAccount> getSetOfAccounts() {
        Set<UserAccount> accounts = new HashSet<>();
        accounts.add(new UserAccount("Login","Password",new Date()));
        accounts.add(new UserAccount("Login2","Password2",new Date()));

        return accounts;
    }
}