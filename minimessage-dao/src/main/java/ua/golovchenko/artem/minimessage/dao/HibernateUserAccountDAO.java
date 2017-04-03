package ua.golovchenko.artem.minimessage.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import java.util.List;

/**
 * Created by головченко on 19.03.2017.
 * DAO class for UserAccount class
 * @author Golovchenko Artem
 */

@Repository
@Transactional(propagation= Propagation.REQUIRED)
public class HibernateUserAccountDAO implements UserAccountDAO {

    private SessionFactory sessionFactory;


    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(UserAccount user) {
        currentSession().save(user);
    }

    @Override
    @Transactional(readOnly = true, propagation= Propagation.SUPPORTS)
    public UserAccount get(Long id) {
        return (UserAccount) currentSession().get(UserAccount.class,id);
    }

    @Override
    public void delete(Long userId) {
        currentSession().delete(get(userId));
    }

    @Override
    public void update(UserAccount user) {
        currentSession().update(user);

    }

    @Override
    @Transactional(readOnly = true, propagation= Propagation.SUPPORTS)
    public List<UserAccount> findAll() {
        Criteria criteria = currentSession().createCriteria(UserAccount.class);
        List<UserAccount> accounts = criteria.list();
        return accounts;
    }
}
