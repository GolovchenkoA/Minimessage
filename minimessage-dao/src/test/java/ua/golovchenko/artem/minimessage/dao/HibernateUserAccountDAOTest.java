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


import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:persistence-context-dev.xml"})
public class HibernateUserAccountDAOTest {

    @Autowired
    private UserAccountDAO accountDAO;

    @Autowired
    UserAccount account;

    @Before
    public void setUp(){
        account.setUsername("UserLogin");
        account.setPassword("Pa$$W0Rd");
        account.setCreated(new Date());
    }



    @Test
    public void userAccountMustBeEmpty(){
        assertTrue(accountDAO.findAll().isEmpty());
    }


    @Test
    @Transactional
    @Rollback(true)
    public void testAddAccountThenGetAccount() throws Exception {
        accountDAO.add(account);

        assertThat(accountDAO.findAll().size(),is(1));
        assertEquals(account,accountDAO.get(1L));
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