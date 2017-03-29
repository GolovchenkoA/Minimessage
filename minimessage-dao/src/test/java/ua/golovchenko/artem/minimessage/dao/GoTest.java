package ua.golovchenko.artem.minimessage.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.golovchenko.artem.config.PersistenceContextMysqlDev;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertTrue;

/**
 * Created by головченко on 29.03.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:persistence-context-mysql-dev.xml"})
@ContextConfiguration(classes = PersistenceContextMysqlDev.class)
public class GoTest {

    @Autowired
    private UserAccountDAO accountDAO;

    @Autowired
    private DataSource ds;

    //@Autowired
    private UserAccount account;
    private String accountName;
    private String accountPassword;



    @Before
    public void setUp(){
/*        accountName = "UserLogin5";
        accountPassword = "Pa$$W0Rd";
        account.setUsername(accountName);
        account.setPassword(accountPassword);
        account.setCreated(new Date());

        accountDAO.add(account);*/
    }


/*    @After
    @Transactional
    public void tearDown() {
        // Clear DB after tests
        try {
            clearDatabase();
        } catch (Exception e) {
            e.getMessage();
        }
    }*/


    @Ignore
    @Test
    public void userAccountMustBeEmpty() throws Exception{
        assertTrue(accountDAO.findAll().isEmpty());
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
