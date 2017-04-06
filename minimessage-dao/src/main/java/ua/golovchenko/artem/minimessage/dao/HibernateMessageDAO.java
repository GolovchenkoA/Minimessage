package ua.golovchenko.artem.minimessage.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.golovchenko.artem.minimessage.model.Message;

import java.util.List;

/**
 * Created by головченко on 17.03.2017.
 *  DAO class for UserAccount class
 *
 * @author Golovchenko Artem
 */

@Repository
@Transactional(propagation= Propagation.REQUIRED)
public class HibernateMessageDAO implements MessageDAO{

    private SessionFactory sessionFactory;

/*    @Autowired
    public HibernateMessageDAO() {//<co id="co_injectSessionFactory"/>
        this.sessionFactory = sessionFactory;
    }*/

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Message message) {
        currentSession().save(message);
    }

    @Override
    @Transactional(readOnly = true, propagation= Propagation.SUPPORTS)
    public Message get(Long id) {
        return (Message) currentSession().get(Message.class, id);
    }

    @Override
    public void delete(Long id) {
        currentSession().delete(get(id));
    }

    @Override
    public void update(Message message) {currentSession().update(message);}

    @Override
    @Transactional(readOnly = true, propagation= Propagation.SUPPORTS)
    public List<Message> findAll() {
        Criteria criteria = currentSession().createCriteria(Message.class);
        List<Message> messages = criteria.list();
        return messages;
    }

    @Override
    @Transactional(readOnly = true, propagation= Propagation.SUPPORTS)
    public List<Message> findAllbyUserId(Long userId) {
        Criteria criteria = currentSession().createCriteria(Message.class);
        criteria.createAlias("account", "account");
        criteria.add(Restrictions.like("account.id", userId));
        List<Message> messages = criteria.list();

        return messages;
    }
}
