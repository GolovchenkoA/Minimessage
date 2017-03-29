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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:persistence-context-dev.xml"})
public class HibernateUserAccountDAOTest {

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
    public void testAddAccountThenGetAccount() throws Exception {
        // Warning: In-memory hsqldb can remember user id between tests and this test FAIL

        assertTrue(accountDAO.findAll().isEmpty());

        accountDAO.add(account);
        accountDAO.add(account2);

        assertFalse(accountDAO.findAll().isEmpty());
        assertThat(accountDAO.findAll().size(),is(2));
        assertEquals(account, accountDAO.findAll().get(0));
        assertEquals(account2,accountDAO.findAll().get(1));
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
}