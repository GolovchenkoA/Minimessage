package ua.golovchenko.artem.minimessage.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.golovchenko.artem.minimessage.model.AccountRole;
import ua.golovchenko.artem.minimessage.model.AccountRoleImpl;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:persistence-context-mysql-dev.xml"})
public class HibernateAccountRoleDAOTest {

    @Autowired
    AccountRoleDAO roleDAO;

    @Test
    @Transactional
    @Rollback(true)
    public void testAddRole(){

        String roleName = "ROLE_NAME";
        AccountRole role = new AccountRoleImpl(roleName);

        roleDAO.add(role);


        AccountRole roleInDB = roleDAO.get(roleName);

        assertEquals("Role Name", role, roleInDB);
    }
}