package ua.golovchenko.artem.minimessage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.golovchenko.artem.minimessage.dao.AccountRoleDAO;
import ua.golovchenko.artem.minimessage.model.AccountRole;

import java.util.List;

/**
 * Created by Artem on 17.04.2017.
 */

@Service
@Transactional(propagation= Propagation.REQUIRED)
public class RolesServiceImpl implements RolesService {

    private AccountRoleDAO accountRoleDAO;

    @Autowired
    public void setAccountRoleDAO(AccountRoleDAO accountRoleDAO) {
        this.accountRoleDAO = accountRoleDAO;
    }


    @Override
    public Long add(AccountRole role) {
        return accountRoleDAO.add(role);
    }

    @Override
    public void delete(AccountRole role) {
        accountRoleDAO.delete(role);
    }

    @Override
    public List<AccountRole> listRoles() {
        return accountRoleDAO.listRoles();
    }
}
