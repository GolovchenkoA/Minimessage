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
import ua.golovchenko.artem.minimessage.model.UserAccount;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

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
        UserAccount account = getNewUserAccount();

        UserAccountDAO accountDAO = mock(UserAccountDAO.class);
        when(accountDAO.add(account)).thenReturn(1L).thenThrow(new DataIntegrityViolationException("nested exception is org.hibernate.exception.ConstraintViolationException"));
        accountDAO.add(account);
        accountDAO.add(account);

    }



    @Ignore
    @Test
    @Transactional
    @Rollback(true)
    public void testAddAccountThenGetAccountByID() throws Exception {
        UserAccount userAccount = getNewUserAccount();
        Long userId = accountDAO.add(userAccount);

        assertNotNull(userId);

    }


    @Test
    @Transactional
    @Rollback(true)
    public void testGetUserAccountByLoginAndPassword() throws Exception {
        UserAccount userAccount = getNewUserAccount();
        accountDAO.add(userAccount);

        assertEquals(userAccount,accountDAO.get(DEFAULT_USER_ACCOUNT,DEFAULT_USER_ACCOUNT_PASSWORD));

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


    private UserAccount getNewUserAccount(){
        return new UserAccount(DEFAULT_USER_ACCOUNT,DEFAULT_USER_ACCOUNT_PASSWORD,new Date());
    }
}