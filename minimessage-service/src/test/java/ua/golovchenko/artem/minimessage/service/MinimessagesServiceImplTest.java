package ua.golovchenko.artem.minimessage.service;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.golovchenko.artem.minimessage.dao.MessageDAO;
import ua.golovchenko.artem.minimessage.dao.UserAccountDAO;
import ua.golovchenko.artem.minimessage.model.Message;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-context-dev.xml"})
public class MinimessagesServiceImplTest {

    //@Autowired
    private MinimessagesServiceImpl minimessagesService;
    private UserAccountDAO userAccountDAO;
    private MessageDAO messageDAO;

    @Captor
    ArgumentCaptor<UserAccount> argumentCaptor;

    private UserAccount userAccount = mock(UserAccount.class);

    @Before
    public void setUp(){
        minimessagesService = new MinimessagesServiceImpl();
        userAccountDAO = mock(UserAccountDAO.class);
        minimessagesService.setAccountDAO(userAccountDAO);
        messageDAO = mock(MessageDAO.class);
        minimessagesService.setMessageDAO(messageDAO);
    }

    @Test
    public void testMethodSaveAccountMustCallMethodDaoAddIfAccountNotExists(){
        UserAccount dummyAccount = getDummyUserAccountWithoutID();

        minimessagesService.saveAccount(dummyAccount);

        verify(userAccountDAO,times(1)).add(dummyAccount);
    }


        @Test
    public void testMethodSaveAccountMustCallMethodDaoUpdateIfAccountExists(){
        UserAccount dummyAccount = getDummyUserAccountWithID();

        minimessagesService.saveAccount(dummyAccount);

        verify(userAccountDAO,times(1)).update(dummyAccount);
    }

    @Test
    public void testMethodGetAccountByIdMustCallMethodDaoGet() {
        minimessagesService.getAccountById(1L);

        verify(userAccountDAO,times(1)).get(1L);
    }

    @Test
    public void testMethodGetAccountByLoginMustCallMethodDaoGet() {
        minimessagesService.getAccountByLogin("Login");

        verify(userAccountDAO,times(1)).get("Login");
    }


    @Test
    public void testGetAllAccountsMustCallMethodDAOFindAll(){
        List<UserAccount> dummyUserAccounts = getListDummyUserAccounts();

        when(userAccountDAO.findAll()).thenReturn(dummyUserAccounts);
        List<UserAccount> UserAccountsInDB = minimessagesService.getAllAccounts();

        assertEquals("All accounts in DB",dummyUserAccounts,UserAccountsInDB);
    }

    @Test
    public void testDeleteMessageMustCallMethodDaoDeleteMessage(){
        minimessagesService.deleteMessage(1L);

        verify(userAccountDAO,times(1)).delete(1L);
    }

    @Test
    public void testLoginExistsMustCallMethodDaoLoginExists(){
        minimessagesService.loginExists("login");

        verify(userAccountDAO, times(1)).loginExists("login");
    }

    @Test
    public void testUpdateUserAccountMustCallMethodDaoUpdateUserAccount(){
        UserAccount account = mock(UserAccount.class);
        minimessagesService.update(account);
        verify(userAccountDAO,times(1)).update(account);
    }

    @Test
    public void testSaveMessageMustCallMethodMessageDaoAddMessage(){
        Message dummyMessage = mock(Message.class);
        minimessagesService.saveMessage(dummyMessage);

        verify(messageDAO, times(1)).add(dummyMessage);
    }


    @Test
    public void testGetMessagesForAccountMustCallMethodUserAccountDaoGetMessagesForAccount(){
        UserAccount account = getDummyUserAccountWithoutID();

        minimessagesService.getMessagesForAccount(account);

        verify(userAccountDAO,times(1)).getMessagesForAccount(account);
    }

    @Ignore // can't write this test
    @Test
    public void testGetMessagesForAccountLoginMustCallMethodUserAccountDaoGetMessagesForAccountLogin(){

        UserAccount account = getDummyUserAccountWithID();
        String login = account.getUsername();

        when(userAccountDAO.get(login)).thenReturn(account);
        minimessagesService.getMessagesForAccount(login);

        verify(userAccountDAO, times(1)).get(account.getUsername());
        verify(userAccountDAO,times(1)).getMessagesForAccount(account);

    }



    // Test Utility Methods
    private UserAccount getDummyUserAccountWithoutID(){
        return new UserAccount("dummyAccount", "Password", new Date() );
    }

    private UserAccount getDummyUserAccountWithID(){
        UserAccount account =  new UserAccount("dummyAccount", "Password", new Date());
        account.setId(1L);
        return account;
    }

    private List<UserAccount> getListDummyUserAccounts(){
        List<UserAccount> accounts = new ArrayList<>();

        accounts.add(new UserAccount("dummyAccount1", "Password", new Date()));
        accounts.add(new UserAccount("dummyAccount2", "Password", new Date()));

        return accounts;
    }


}