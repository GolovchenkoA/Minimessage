package ua.golovchenko.artem.minimessage.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:persistence-context-dev.xml"})
public class HibernateUserAccountDAOTest {

    private static final String DEFAULT_USER_ACCOUNT = "DefaultUserAccount";
    private static final String DEFAULT_USER_ACCOUNT_PASSWORD = "DefaultUserPassword";


    @Autowired
    private UserAccountDAO accountDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private DataSource ds;


    private UserAccount account;
    private String accountName;
    private String accountPassword;


    private UserAccount account2;
    private String account2Name;
    private String account2Password;



    @Before
    public void setUp(){

        account = new UserAccount();
        accountName = "UserLogin_1";
        accountPassword = "Pa$$W0Rd";
        account.setUsername(accountName);
        account.setPassword(accountPassword);
        account.setCreated(new Date());

        account2 = new UserAccount();
        account2Name = "UserLogin_2";
        account2Password = "Pa$$W0Rd_2";
        account2.setUsername(account2Name);
        account2.setPassword(account2Password);
        account2.setCreated(new Date());


    }


/*    @After
    public void tearDown() {
        // Clear DB after tests
        try {
            clearDatabase();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }*/


    @Test
    @Transactional
    @Rollback(true)
    public void testAddAccountThenFindAccount() throws Exception {

        assertTrue(accountDAO.findAll().isEmpty());

        accountDAO.add(account);
        accountDAO.add(account2);

        assertFalse(accountDAO.findAll().isEmpty());
        assertThat(accountDAO.findAll().size(),is(2));
        assertEquals(account, accountDAO.findAll().get(0));
        assertEquals(account2,accountDAO.findAll().get(1));
    }

    @Test(expected=DataIntegrityViolationException.class)
    public void testThrowExceptionWhenTryToAddAccountWithSameName_WithMock(){
        UserAccount account = getDefaultUserAccountWithoutID();

        UserAccountDAO accountDAO = mock(UserAccountDAO.class);
        when(accountDAO.add(account)).thenReturn(1L).thenThrow(new DataIntegrityViolationException("nested exception is org.hibernate.exception.ConstraintViolationException"));
        accountDAO.add(account);
        accountDAO.add(account);

    }



    @Ignore
    @Test
    @Transactional
    @Rollback(true)
    public void testAddAccount() throws Exception {
        UserAccount userAccount = getDefaultUserAccountWithoutID();
        Long userId = accountDAO.add(userAccount);

        assertNotNull(userId);

    }


    @Test
    @Transactional
    @Rollback(true)
    public void testGetUserAccountByLoginAndPassword() throws Exception {
        UserAccount userAccount = getDefaultUserAccountWithoutID();
        accountDAO.add(userAccount);
        UserAccount userAccountInDB = accountDAO.get(DEFAULT_USER_ACCOUNT, DEFAULT_USER_ACCOUNT_PASSWORD);

        assertEquals(userAccount,userAccountInDB);

    }


    @Test
    @Transactional
    @Rollback(true)
    public void testDelete() throws Exception {

        assertTrue(accountDAO.findAll().isEmpty());

        accountDAO.add(account);
        assertTrue(accountDAO.findAll().contains(account));

        accountDAO.delete(account.getId());
        assertFalse(accountDAO.findAll().contains(account));
    }


      @Test
      @Transactional
      @Rollback(true)
    public void testUpdate() throws Exception {

          accountDAO.add(account);
          account.setPassword("NewPassword");
          accountDAO.update(account);

          assertEquals(account,accountDAO.get(account.getId()));
    }


    @Test
    @Transactional
    @Rollback(true)
    public void testFindAll() throws Exception {

        assertTrue(accountDAO.findAll().isEmpty());

        accountDAO.add(account);
        accountDAO.add(account2);

        assertFalse(accountDAO.findAll().isEmpty());

        List<UserAccount> allAccounts = accountDAO.findAll();
        assertTrue(allAccounts.contains(account));
        assertTrue(allAccounts.contains(account2));
    }


    @Test
    @Transactional
    @Rollback(true)
    public void testGetMessagesForAccountMustReturnListMessagesOnlyForOneAccount() throws Exception {

        UserAccount account1 = getDefaultUserAccountWithoutID();
        Long account1_ID = accountDAO.add(account1);

        UserAccount account2 = new UserAccount("account2","account2Password", new Date());
        Long account2_ID = accountDAO.add(account2);

        addMessagesInDBForAccounts(account1,account2);

        // Assertions
        Set<Message> messagesForAccount1 = new HashSet<Message>(accountDAO.getMessagesForAccount(account1));
        assertNotNull(messagesForAccount1);
        assertThat("Number of messages in the DB must be 2", messagesForAccount1.size(), is(2));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetMessagesForAccountLoginMustReturnListMessagesOnlyForOneAccountLogin() throws Exception {

        UserAccount account1 = getDefaultUserAccountWithoutID();
        Long account1_ID = accountDAO.add(account1);

        UserAccount account2 = new UserAccount("account2","account2Password", new Date());
        Long account2_ID = accountDAO.add(account2);

        addMessagesInDBForAccounts(account1,account2);

        // Assertions
        Set<Message> messagesForAccount1 = new HashSet<Message>(accountDAO.getMessagesForAccount(DEFAULT_USER_ACCOUNT));
        assertNotNull(messagesForAccount1);
        assertThat("Number of messages in the DB must be 2", messagesForAccount1.size(), is(2));
    }




    @Test
    @Transactional
    @Rollback(true)
    public void testGetUserAccountByLogin(){
        UserAccount userAccount = getDefaultUserAccountWithoutID();
        accountDAO.add(userAccount);

        assertEquals(userAccount, accountDAO.get(DEFAULT_USER_ACCOUNT));

    }


    @Test
    @Transactional
    @Rollback(true)
    public void testLoginExistsMustReturnTrueIfAccountExistsInDB(){
        UserAccount userAccount = getDefaultUserAccountWithoutID();
        accountDAO.add(userAccount);

        assertTrue(accountDAO.loginExists(DEFAULT_USER_ACCOUNT));
    }


    @Test
    @Transactional
    @Rollback(true)
    public void testLoginExistsMustReturnFalseIfAccountExistsInDB(){
        UserAccount userAccount = getDefaultUserAccountWithoutID();
        //accountDAO.add(userAccount);

        assertFalse(accountDAO.loginExists(DEFAULT_USER_ACCOUNT));
    }



    //Support methods

    private void addMessagesInDBForAccounts(UserAccount account1, UserAccount account2) {
        Message message1 = new Message(account1,"User 1 Text 1", new Date());
        Message message2 = new Message(account1,"User 1 Text 2", new Date());
        Message message3 = new Message(account2,"User 2 Text 1", new Date());

        messageDAO.add(message1);
        messageDAO.add(message2);
        messageDAO.add(message3);
    }


    //Test Utility Methods
    private void clearDatabase() throws Exception {

        Statement stmt;

        try( Connection connection = ds.getConnection()){
            stmt = connection.createStatement();
            //stmt.executeUpdate("DROP SCHEMA PUBLIC CASCADE;");
            //stmt.executeUpdate("TRUNCATE SCHEMA PUBLIC AND COMMIT;");
            stmt.executeUpdate("TRUNCATE TABLE USER_ACCOUNTS; ");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    private UserAccount getDefaultUserAccountWithoutID(){
        return new UserAccount(DEFAULT_USER_ACCOUNT,DEFAULT_USER_ACCOUNT_PASSWORD,new Date());
    }

}