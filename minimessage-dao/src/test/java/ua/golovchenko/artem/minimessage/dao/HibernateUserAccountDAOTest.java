package ua.golovchenko.artem.minimessage.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:persistence-context-dev.xml"})
public class HibernateUserAccountDAOTest {

    @Autowired
    private UserAccountDAO accountDAO;

    @Autowired
    private DataSource ds;

    @Autowired
    private UserAccount account;
    private String accountName;
    private String accountPassword;



    @Before
    public void setUp(){
        accountName = "UserLogin5";
        accountPassword = "Pa$$W0Rd";
        account.setUsername(accountName);
        account.setPassword(accountPassword);
        account.setCreated(new Date());
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
    public void userAccountMustBeEmpty() throws Exception{
        assertTrue(accountDAO.findAll().isEmpty());
    }


/*
    @Test
    @Transactional
    @Rollback(true)
    public void testAddAccountThenGetAccount() throws Exception {
        // Warning: In-memory hsqldb can remember user id between tests and this test FAIL
        accountDAO.add(account);

        assertThat(accountDAO.findAll().size(),is(1));
        assertEquals(account,accountDAO.get(1L));
    }
*/


    @Test
    @Transactional
    @Rollback(true)
    public void testDelete() throws Exception {

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

        UserAccount firstAccount = new UserAccount();
        firstAccount.setUsername("User 1");
        firstAccount.setPassword("Pa$$word");
        firstAccount.setCreated(new Date());

        UserAccount secondAccount = new UserAccount();
        secondAccount.setUsername("User 2");
        secondAccount.setPassword("Pa$$word2");
        secondAccount.setCreated(new Date());

        accountDAO.add(firstAccount);
        accountDAO.add(secondAccount);

        List<UserAccount> allAccounts = accountDAO.findAll();

        assertTrue(allAccounts.contains(firstAccount));
        assertTrue(allAccounts.contains(secondAccount));
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