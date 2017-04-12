package ua.golovchenko.artem.minimessage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.golovchenko.artem.minimessage.dao.MessageDAO;
import ua.golovchenko.artem.minimessage.dao.UserAccountDAO;
import ua.golovchenko.artem.minimessage.model.Message;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import java.util.List;

/**
 * Created by головченко on 02.04.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class MinimessagesServiceImpl implements MinimessagesService {


    private UserAccountDAO accountDAO;
    private MessageDAO messageDAO;

    @Autowired
    public void setAccountDAO(UserAccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    @Autowired
    public void setMessageDAO(MessageDAO messageDAO){this.messageDAO = messageDAO;}

    @Override
    public void saveAccount(UserAccount user) {

        if(user.getId() == null){
            accountDAO.add(user);
        }else {
            accountDAO.update(user);
        }
    }

    @Override
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public UserAccount getAccountById(Long userId) {
        return accountDAO.get(userId);
    }


    @Override
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public boolean loginExists(String login) {
        return accountDAO.loginExists(login);
    }



    @Override
    public void deleteMessage(Long id) {
        accountDAO.delete(id);
    }

    @Override
    public void update(UserAccount user) {
        accountDAO.update(user);
    }

    @Override
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public List<UserAccount> getAllAccounts() {
        return accountDAO.findAll();
    }

    @Override
    public void startFollowing(UserAccount follower, UserAccount followee) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void saveMessage(Message message) {
        messageDAO.add(message);
    }

    @Override
    public Message getMessageById(Long id) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<Message> getRecentMessages(int count) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<Message> getMessagesForAccount(UserAccount account) {
        return accountDAO.getMessagesForAccount(account);
    }

    @Override
    public List<Message> getMessagesForAccount(String login) {
        UserAccount account = accountDAO.get(login);
        return getMessagesForAccount(account);
    }
}
