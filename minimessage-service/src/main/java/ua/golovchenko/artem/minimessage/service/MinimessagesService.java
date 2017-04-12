package ua.golovchenko.artem.minimessage.service;

import ua.golovchenko.artem.minimessage.model.Message;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import java.util.List;

/**
 * Created by головченко on 02.04.2017.
 */
public interface MinimessagesService {
    void saveAccount(UserAccount user);
    UserAccount getAccountById(Long userId);
    boolean loginExists(String login);
    void update(UserAccount user);

    void startFollowing(UserAccount follower, UserAccount followee);

    void saveMessage(Message message);
    Message getMessageById(Long id);
    void deleteMessage(Long id);

    List<UserAccount> getAllAccounts();

    List<Message> getRecentMessages(int count);
    List<Message> getMessagesForAccount(UserAccount account);
    List<Message> getMessagesForAccount(String login);
}
