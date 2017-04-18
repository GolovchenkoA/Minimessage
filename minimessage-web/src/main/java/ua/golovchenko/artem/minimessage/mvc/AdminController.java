package ua.golovchenko.artem.minimessage.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.golovchenko.artem.minimessage.model.AccountRole;
import ua.golovchenko.artem.minimessage.model.AccountRoleImpl;
import ua.golovchenko.artem.minimessage.model.UserAccount;
import ua.golovchenko.artem.minimessage.service.MinimessagesService;
import ua.golovchenko.artem.minimessage.service.RolesService;

import javax.inject.Inject;
import javax.validation.Valid;

import java.util.Collections;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Artem on 17.04.2017.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RolesService rolesService;
    private final MinimessagesService minimessagesService;

    @Inject
    public AdminController(RolesService rolesService, MinimessagesService minimessagesService) {
        this.rolesService = rolesService;
        this.minimessagesService = minimessagesService;
    }




    @RequestMapping(value = "roles", method = POST )
    public String addRole(@Valid AccountRoleImpl role, BindingResult result){

        if(result.hasErrors()){
            return "admin/roles";
        }

        rolesService.add(role);
        return "admin/roles";
    }

    @RequestMapping(value = "roles", method = GET)
    public String rolesInfo(Model model){
        List<AccountRole> roles = rolesService.listRoles();

        model.addAttribute("roles", roles);
        model.addAttribute("role", new AccountRoleImpl());

        return "admin/roles";
    }


     @RequestMapping(value = "accounts", method = GET)
    public synchronized String accountsInfo(Model model){
        List<UserAccount> accounts = minimessagesService.getAllAccounts();

        model.addAttribute("accounts", accounts);

        return "admin/accounts";
    }


    public MinimessagesService getMinimessagesService() {
        return minimessagesService;
    }
}
