package ua.golovchenko.artem.minimessage.dao;

import ua.golovchenko.artem.minimessage.model.AccountRole;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import java.util.List;

/**
 * Created by Artem on 17.04.2017.
 */
public interface AccountRoleDAO {
    Long add(AccountRole role);
    void delete(AccountRole role);
    AccountRole get(String roleName);

    /*List all roles (unique)  */
    List<AccountRole> listRoles();
    List<UserAccount> listAccountsWithRole(String role_name);
}
