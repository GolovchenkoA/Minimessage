package ua.golovchenko.artem.minimessage.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.golovchenko.artem.minimessage.dao.UserAccountDAO;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-context-dev.xml"})
public class UserAccountServiceImplTest {

    //@Autowired
    private UserAccountServiceImpl userAccountService;
    private UserAccountDAO userAccountDAO;

    @Captor
    ArgumentCaptor<UserAccount> argumentCaptor;

    private UserAccount userAccount = mock(UserAccount.class);

    @Before
    public void setUp(){
        userAccountService = new UserAccountServiceImpl();
        userAccountDAO = mock(UserAccountDAO.class);
        userAccountService.setAccountDAO(userAccountDAO);
    }

    @Test
    public void testMethodAddMustCallMethodDaoAdd(){
        Long USER_ID_IN_DB = 1L;
        UserAccount dummyAccount = getDummyUserAccount();

        when(userAccountDAO.add(dummyAccount)).thenReturn(USER_ID_IN_DB);
        Long actual_user_id = userAccountService.add(dummyAccount);

        assertEquals("Account ID in DB",actual_user_id, USER_ID_IN_DB);
    }


    @Test
    public void testFindAllMustCallMethodDAOFindAll(){
        List<UserAccount> dummyUserAccounts = getListDummyUserAccounts();

        when(userAccountDAO.findAll()).thenReturn(dummyUserAccounts);
        List<UserAccount> actualUserAccounts = userAccountService.findAll();

        assertEquals("All accounts in DB",actualUserAccounts,dummyUserAccounts);
    }



    // Test Utility Methods
    private UserAccount getDummyUserAccount(){
        return new UserAccount("dummyAccount", "Password", new Date() );
    }

    private List<UserAccount> getListDummyUserAccounts(){
        List<UserAccount> accounts = new ArrayList<>();

        accounts.add(new UserAccount("dummyAccount1", "Password", new Date()));
        accounts.add(new UserAccount("dummyAccount2", "Password", new Date()));

        return accounts;
    }

}