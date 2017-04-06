package ua.golovchenko.artem.minimessage.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import javax.validation.ConstraintViolationException;
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

    /**
    *@return Long . User account id
    *@throws ConstraintViolationException when try to add a same user account. For example with same username
     *
    */
    @Override
    public Long add(UserAccount user) {
        return (Long) currentSession().save(user);
    }

    @Override
    @Transactional(readOnly = true, propagation= Propagation.SUPPORTS)
    public UserAccount get(Long id) {
        return (UserAccount) currentSession().get(UserAccount.class,id);
    }

    @Override
    @Transactional(readOnly = true, propagation= Propagation.SUPPORTS)
    public UserAccount get(String login, String password) {

        Criteria criteria = currentSession().createCriteria(UserAccount.class);
        criteria.add(Restrictions.eq("username", login)).uniqueResult();
        criteria.add(Restrictions.eq("password", password)).uniqueResult();

        return (UserAccount) criteria.list().get(0);
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
