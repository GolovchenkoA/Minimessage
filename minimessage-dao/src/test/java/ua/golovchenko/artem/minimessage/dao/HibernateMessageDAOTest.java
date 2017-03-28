package ua.golovchenko.artem.minimessage.dao;

import org.junit.Before;
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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:persistence-context-dev.xml"})
@Transactional
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


    @Before
    public void setUp() throws Exception {
        // Init message
        message.setAccount(getUserAccount());
        messageText =  "MessageText";
        message.setText(messageText);
        message.setCreated(new Date());
/*        initMocks(this);
        when(message.getAccount()).thenReturn(getUserAccount());*/

        //Init User Account and Add to Database. All messages have foreign key to account id
        userAccount = getUserAccount();
        accountDAO.add(userAccount);

    }

/*    @After
    public void tearDown() throws Exception {
        // Clear DB after tests
        try {
            clearDatabase();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }*/


    @Test
    @Transactional
    public void allMessagesMustBeEmpty() throws Exception{

        assertTrue(messageDAO.findAll().isEmpty());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAdd() throws Exception {


        messageDAO.add(message);
        List<Message> allMessages = messageDAO.findAll();

        assertNotNull(allMessages);
        assertEquals(allMessages.size(),1);
        assertEquals(message, allMessages.get(0));
        assertEquals(messageText, messageDAO.get(1L).getText());
        assertEquals(userAccount,  messageDAO.get(1L).getAccount());
    }
/*

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }*/

    //@Ignore
    @Test
    @Transactional
    @Rollback(true)
    public void testFindAll() throws Exception {
        // Init messages
        String text1 = "Message Text 1";
        String text2 = "Message Text 2";

        Message message1 = new Message(getUserAccount(),text1);
        message1.setAccount(getUserAccount());
        message1.setCreated(new Date());
        Message message2 = new Message(getUserAccount(),text2);
        message2.setAccount(getUserAccount());
        message2.setCreated(new Date());


        assertFalse(message.equals(message1));
        assertFalse(message1.equals(message2));
/*        messageDAO.add(message1);
        messageDAO.add(message2);

        // Assertions
        assertNotNull(messageDAO.findAll());
        assertThat(messageDAO.findAll().size(), is(2));
        assertTrue(messageDAO.findAll().contains(message1));
        assertTrue(messageDAO.findAll().contains(message2));*/
    }

/*    @Test
    public void testFindAllbyUserId() throws Exception {

    }*/

    private UserAccount getUserAccount(){
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("UserName");
        userAccount.setPassword("UserPa$$W0Rd");
        userAccount.setId(1L);
        userAccount.setCreated(new Date());

        return userAccount;
    }

    private void clearDatabase() throws Exception {


        Connection connection = null;
        try {
            connection = ds.getConnection();
            try {
                Statement stmt = connection.createStatement();
                try {
                    stmt.execute("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK");
                    connection.commit();
                } finally {
                    stmt.close();
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new Exception(e);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }
}