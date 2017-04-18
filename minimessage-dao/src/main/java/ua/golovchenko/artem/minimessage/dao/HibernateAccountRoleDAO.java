package ua.golovchenko.artem.minimessage.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.golovchenko.artem.minimessage.model.AccountRole;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import java.util.List;

/**
 * Created by Artem on 17.04.2017.
 * <p>DAO class for model AccountRoleDB</p>
 *
 *
 * @author Golovchenko Artem
 */

@Repository
@Transactional(propagation= Propagation.REQUIRED)
public class HibernateAccountRoleDAO implements AccountRoleDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public Long add(AccountRole role) {
        return (Long) currentSession().save(role) ;
    }

    @Override
    public void delete(AccountRole role) {
        currentSession().delete(role);
    }

    @Override
    public AccountRole get(String roleName) {

        Criteria criteria = currentSession().createCriteria(AccountRole.class);
        criteria.add(Restrictions.eq("name", roleName)).uniqueResult();

        return (AccountRole) criteria.list().get(0);
    }

    @Override
    public List<AccountRole> listRoles() throws UnsupportedOperationException{
        Criteria criteria = currentSession().createCriteria(AccountRole.class);
        List<AccountRole> roles = criteria.list();
        return roles;
    }

    @Override
    public List<UserAccount> listAccountsWithRole(String role_name) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
