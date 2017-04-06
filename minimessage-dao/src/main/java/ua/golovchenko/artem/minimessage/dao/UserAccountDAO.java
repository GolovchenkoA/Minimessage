package ua.golovchenko.artem.minimessage.dao;

import ua.golovchenko.artem.minimessage.model.UserAccount;

import java.util.List;

/**
 * Created by головченко on 19.03.2017.
 */
public interface UserAccountDAO {
    Long add (UserAccount user);
    UserAccount get(Long userId);
    void delete(Long id);
    void update(UserAccount user);
     List<UserAccount> findAll();
}
