package ua.golovchenko.artem.minimessage.service;

import ua.golovchenko.artem.minimessage.model.AccountRole;
import ua.golovchenko.artem.minimessage.model.UserAccount;

import java.util.List;

/**
 * Created by Artem on 17.04.2017.
 */
public interface RolesService {
    Long add(AccountRole role);
    void delete(AccountRole role);
    List<AccountRole> listRoles();
}
