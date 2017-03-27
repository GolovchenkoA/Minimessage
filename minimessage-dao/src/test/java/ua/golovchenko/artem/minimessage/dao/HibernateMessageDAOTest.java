package ua.golovchenko.artem.minimessage.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

import static org.hamcrest.CoreMatchers.is;
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

    //@Mock
    private UserAccount userAccount;

    @Before
    public void setUp() throws Exception {
/*        initMocks(this);
        when(message.getAccount()).thenReturn(getUserAccount());*/
        accountDAO.add(getUserAccount());

        //message.setText("Message Text 1");

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

    /*    @Test
    public void testAdd() throws Exception {
        messageDAO.add(message);
        List<Message> allMessages = messageDAO.findAll();

        assertNotNull(allMessages);
        assertEquals(message, allMessages.get(1));
    }*/
/*
    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }*/

    @Test
    public void testFindAll() throws Exception {
        // Init messages
        String text1 = "Message Text 1";
        String text2 = "Message Text 2";

        Message message1 = new Message(getUserAccount(),text1);
        Message message2 = new Message(getUserAccount(),text2);

        messageDAO.add(message1);
        messageDAO.add(message2);

        // Assertions
        assertNotNull(messageDAO.findAll());
        assertThat(messageDAO.findAll().size(), is(2));
        assertTrue(messageDAO.findAll().contains(message1));
        assertTrue(messageDAO.findAll().contains(message2));
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