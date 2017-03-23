package ua.golovchenko.artem.minimessage.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import java.util.Date;

import static org.junit.Assert.assertNull;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:persistence-context-dev.xml"})
public class HibernateUserAccountDAOTest {

    @Autowired
    private UserAccountDAO accountDAO;
    //private SessionFactory sessionFactory;

/*    @Autowired
    UserAccount account;*/

    @Before
    public void setUp(){
        accountDAO = new HibernateUserAccountDAO();

        UserAccount account = new UserAccount();
        account.setUsername("UserLogin");
        account.setPassword("Pa$$W0Rd");
        account.setCreated(new Date());
    }


    @Test
    public void testAdd() throws Exception {
        //List<UserAccount> accounts = new ArrayList<>();
            assertNull(accountDAO.findAll());
    }

/*    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testFindAll() throws Exception {

    }*/
}