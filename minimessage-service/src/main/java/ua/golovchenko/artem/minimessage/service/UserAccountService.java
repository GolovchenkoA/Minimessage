package ua.golovchenko.artem.minimessage.service;

import ua.golovchenko.artem.minimessage.model.UserAccount;

import java.util.List;

/**
 * Created by головченко on 02.04.2017.
 */
public interface UserAccountService {
    void add (UserAccount user);
    UserAccount get(Long userId);
    UserAccount get(String login);
    void delete(Long id);
    void update(UserAccount user);
    List<UserAccount> findAll();
}
