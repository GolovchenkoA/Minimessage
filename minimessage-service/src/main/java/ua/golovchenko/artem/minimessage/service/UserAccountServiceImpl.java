package ua.golovchenko.artem.minimessage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.golovchenko.artem.minimessage.dao.UserAccountDAO;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import java.util.List;

/**
 * Created by головченко on 02.04.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class UserAccountServiceImpl implements UserAccountService {


    private UserAccountDAO accountDAO;

    @Autowired
    public void setAccountDAO(UserAccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }


    @Override
    public Long add(UserAccount user) {
        return accountDAO.add(user);
    }

    @Override
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public UserAccount get(Long userId) {
        return accountDAO.get(userId);
    }

    @Override
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public UserAccount get(String login) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(UserAccount user) {

    }

    @Override
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public List<UserAccount> findAll() {
        return accountDAO.findAll();
    }
}
