package ua.golovchenko.artem.minimessage.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.golovchenko.artem.minimessage.model.Text;
import ua.golovchenko.artem.minimessage.model.TextImpl;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:persistence-context-dev.xml"})
public class HibernateTextDAOTest {

    @Autowired
    HibernateTextDAO textDAO;

    Text text;

    @Before
    public void setUp(){
        text = new TextImpl("HibernateTextDAOTest");
    }

    @Test
    public void testAdd() throws Exception {
        textDAO.add(text);
        assertTrue(Arrays.asList(textDAO.findAll()).contains(text));
    }
}