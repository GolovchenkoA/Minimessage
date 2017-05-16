package ua.golovchenko.artem.minimessage.mvc;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import ua.golovchenko.artem.minimessage.model.AccountRole;
import ua.golovchenko.artem.minimessage.model.AccountRoleImpl;
import ua.golovchenko.artem.minimessage.model.Message;

import java.util.HashSet;

/**
 * Created by Artem on 19.04.2017.
 *
 * Usage when input invalide role end on error forward to "admin/roles" (method addRole(@Valid AccountRoleImpl role, BindingResult result) POST )we need to insert model for rolesInfo(Model model) (Method GET)
 *
 */
public class RoleBeanInterceptor implements WebRequestInterceptor {

    public void afterCompletion(WebRequest webRequest,Exception arg1) throws Exception {}

    public void postHandle(WebRequest webRequest, ModelMap model)
            throws Exception {
        if(model != null) {
            model.addAttribute("role",new AccountRoleImpl());
            //model.addAttribute("roles",new HashSet<AccountRole>());
        }
    }

    public void preHandle(WebRequest webRequest) throws Exception {}

}
