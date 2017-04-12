package ua.golovchenko.artem.minimessage.dao;

import ua.golovchenko.artem.minimessage.model.Message;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import java.util.List;

/**
 * Created by головченко on 19.03.2017.
 */
public interface UserAccountDAO {
    Long add (UserAccount user);
    UserAccount get(Long userId);
    UserAccount get(String login, String password);

    UserAccount get(String login);
    boolean loginExists(String login);

    void delete(Long id);
    void update(UserAccount user);

     List<UserAccount> findAll();
    List<Message> getMessagesForAccount(String login);
    List<Message> getMessagesForAccount(UserAccount account);


}
