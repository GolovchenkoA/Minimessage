package ua.golovchenko.artem.minimessage.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.golovchenko.artem.minimessage.dao.UserAccountDAO;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-context-dev.xml"})
public class UserAccountServiceImplTest {

    @Autowired
    private UserAccountService userAccountService;
    private UserAccountDAO userAccountDAO;

    private UserAccount userAccount;

    @Before
    public void setUp(){
        userAccount = new UserAccount("UserAccountLogin", "Password", new Date() );
        //userAccountService = new UserAccountServiceImpl();
        userAccountDAO = mock(UserAccountDAO.class);
    }


    @Test
    @Transactional
    @Rollback(true)
    public void testAddUserAccount(){
        List<UserAccount> allUsers = new ArrayList<UserAccount>(1);
        allUsers.add(userAccount);
        when(userAccountDAO.findAll()).thenReturn(allUsers);

        userAccountService.add(userAccount);
        assertEquals(1, userAccountService.findAll().size());
    }



    @Test
    @Transactional
    @Rollback(true)
    public void testAddUserAccountMustCallAddMethodInDAOClass(){

        userAccountService.add(userAccount);
        verify(userAccountDAO).add(userAccount);

    }

}