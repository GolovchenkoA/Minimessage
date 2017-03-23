package ua.golovchenko.artem.minimessage.dao;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:persistence-context-dev.xml"})
public class HibernateUserAccountDAOTest {

    @Autowired
    private SessionFactory sessionFactory;


    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testFindAll() throws Exception {

    }
}