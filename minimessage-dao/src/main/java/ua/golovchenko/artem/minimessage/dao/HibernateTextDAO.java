package ua.golovchenko.artem.minimessage.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.golovchenko.artem.minimessage.model.Text;
import ua.golovchenko.artem.minimessage.model.TextImpl;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import java.util.List;

/**
 * Created by головченко on 19.03.2017.
 *  This class created just for testing configuration spring and hibernate
 *  @author Golovchenko Artem
 */

@Repository
@Transactional(propagation= Propagation.REQUIRED)
public class HibernateTextDAO implements TextDAO {

    private SessionFactory sessionFactory;


    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void add(Text text) {
        currentSession().saveOrUpdate(text);
    }

    @Override
    public Text get(Long textId) {

        return (Text)currentSession().get(TextImpl.class,textId);
    }

    @Override
    public List<Text> findAll() {
        Criteria criteria = currentSession().createCriteria(UserAccount.class);
        List<Text> texts = criteria.list();
        return texts;
    }
}
