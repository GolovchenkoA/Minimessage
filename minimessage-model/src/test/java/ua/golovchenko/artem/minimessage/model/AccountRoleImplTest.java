package ua.golovchenko.artem.minimessage.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class AccountRoleImplTest {

    private AccountRole role;


    @Before
    public void setUp(){
        role = new AccountRoleImpl();
    }


    @Test
    public void testSetRoleNameThenGetRoleNameMustBeEquals() {
        String roleName = "newRole";
        role.setName(roleName);
        assertEquals("Role name",roleName,role.getName());
    }

}