package ua.golovchenko.artem.dao;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.golovchenko.artem.minimessage.model.Message;

import java.util.List;

/**
 * Created by головченко on 17.03.2017.
 */

@Repository
public class HibernateMessageDAO implements MessageDAO{

    @Autowired
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
    public Message get(Long id) {
        return null;
    }

    @Override
    public void delete(Message message) {

    }

    @Override
    public void update(Message message) {

    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public List<Message> findAllbyUserId(Long userId) {
        return null;
    }
}
