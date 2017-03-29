package ua.golovchenko.artem.minimessage.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.golovchenko.artem.minimessage.model.Message;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:persistence-context-dev.xml"})
public class HibernateMessageDAOTest {

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private UserAccountDAO accountDAO;

    @Autowired
    DataSource ds;

    @Autowired
    //@InjectMocks
    private Message message;
    private String messageText;

    //@Mock
    private UserAccount userAccount;
    //private  Connection connection;

    @Before
    public void setUp() throws Exception {
        //Init User Account and Add to Database. All messages have foreign key to account id
        userAccount = getUserAccount();
        accountDAO.add(userAccount);

        // Init message
        message.setAccount(userAccount);
        messageText =  "MessageText";
        message.setText(messageText);
        message.setCreated(new Date());

    }

    @After
    public void tearDown() throws Exception {
        //Clead DB after tests
        // Delete account that was created in @Before method (setUp())
        accountDAO.delete(userAccount.getId());

        // Clear DB after tests
/*        try {
            clearDatabase();
        } catch (Exception e) {
            fail(e.getMessage());
        }*/
    }



    @Test
    @Transactional
    @Rollback(true)
    public void testAddMessageThenGetMessageMustBeEquals() throws Exception {

        assertTrue(messageDAO.findAll().isEmpty());

        messageDAO.add(message);
        List<Message> allMessages = messageDAO.findAll();

        assertNotNull(allMessages);
        assertEquals(allMessages.size(),1);
        assertEquals(message, messageDAO.get(message.getId()));
        assertEquals(messageText, messageDAO.findAll().get(0).getText());
        assertEquals(userAccount,  messageDAO.findAll().get(0).getAccount());
    }


    @Transactional
    @Rollback(true)
    @Test
    public void testDelete() throws Exception {

        messageDAO.add(message);
        assertThat("Message count in DB must be 1",messageDAO.findAll().size(), is(1));

        messageDAO.delete(message.getId());
        assertThat("Number of messages in the DB must be 0",messageDAO.findAll().size(), is(0));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdate() throws Exception {
        messageDAO.add(message);
        String newText = "New Message Text";

        message.setText(newText);
        messageDAO.update(message);

        assertEquals("Texts must be equals",newText,messageDAO.get(message.getId()).getText());
    }


    @Test
    @Transactional
    @Rollback(true)
    public void testFindAllMessagesAllUsers() throws Exception {
        // Init messages
        String text1 = "Message Text 1";
        String text2 = "Message Text 2";

        Message message1 = new Message(userAccount,text1, new Date());
        Message message2 = new Message(userAccount,text2, new Date());

        messageDAO.add(message1);
        messageDAO.add(message2);


        // Assertions
        assertNotNull(messageDAO.findAll());
        assertThat("Number of messages in the DB must be 2", messageDAO.findAll().size(), is(2));
        assertTrue(messageDAO.findAll().contains(message1));
        assertTrue(messageDAO.findAll().contains(message2));
    }


    @Ignore // method messageDAO.findAllbyUserId is not implemented
    @Test
    @Transactional
    @Rollback(true)
    public void testFindAllMessagesbyUserId() throws Exception {

        String text1 = "Message Text 1";
        String text2 = "Message Text 2";

        Message message1 = new Message(userAccount,text1, new Date());
        Message message2 = new Message(userAccount,text2, new Date());

        Set<Message> userMessages = new HashSet<>(2);
        userMessages.add(message);
        userMessages.add(message1);
        userMessages.add(message2);


        messageDAO.add(message1);
        messageDAO.add(message2);


        // Assertions
        Set<Message> messagesInDB = new HashSet<Message>(messageDAO.findAllbyUserId(userAccount.getId()));
        assertNotNull(messagesInDB);
        assertThat("Number of messages in the DB must be 2",messagesInDB, is(2));
        assertEquals(userMessages,messagesInDB);

    }


    private UserAccount getUserAccount(){
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("UserName");
        userAccount.setPassword("UserPa$$W0Rd");
        //userAccount.setId(1L);
        userAccount.setCreated(new Date());

        return userAccount;
    }

    private void clearDatabase() throws Exception {

        Statement stmt;

        try( Connection connection = ds.getConnection()){
            stmt = connection.createStatement();
            //stmt.executeUpdate("DROP SCHEMA PUBLIC CASCADE;");
            stmt.executeUpdate("TRUNCATE SCHEMA PUBLIC AND COMMIT;");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}