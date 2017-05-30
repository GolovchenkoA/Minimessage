package ua.golovchenko.artem.minimessage.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.golovchenko.artem.minimessage.model.AccountRole;
import ua.golovchenko.artem.minimessage.model.AccountRoleImpl;
import ua.golovchenko.artem.minimessage.model.Message;
import ua.golovchenko.artem.minimessage.model.UserAccount;
import ua.golovchenko.artem.minimessage.service.MinimessagesService;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.security.core.Authentication;
import ua.golovchenko.artem.minimessage.service.RolesService;
import ua.golovchenko.artem.minimessage.service.RolesServiceImpl;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by Artem on 14.04.2017.
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final MinimessagesService minimessagesService;
    private final RolesService rolesService;
    private final Logger logger = LoggerFactory.getLogger(AccountController.class);


    @Inject
    public AccountController(MinimessagesService minimessagesService, RolesService rolesService) {
        this.minimessagesService = minimessagesService;
        this.rolesService = rolesService;
    }


    @RequestMapping(value="/messages", method=GET)
    public String listMessagesForAccount(@RequestParam("account") String login, Model model){
        UserAccount account = minimessagesService.getAccountByLogin(login);
        List<Message> messages = minimessagesService.getMessagesForAccount(login);

        model.addAttribute("account",account);
        model.addAttribute("messagesList",messages);
        return "messages/list";
    }

    @RequestMapping(value="/create_new_account", method = GET)
    public String createAccountProfiler(Model model){
        model.addAttribute("account", new UserAccount());
        return "accounts/edit";
    }

    @RequestMapping(value="/create_new_account",method = POST)
    /*public String addAccountFromForm(@Valid @ModelAttribute("newAccount") UserAccount account, BindingResult bindingResult){*/
    public String addAccountFromForm(@Valid UserAccount account, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "accounts/edit";
        }

        account.setCreated(new Date());
        account.setEnabled(true);

        // Default account role
        Set<AccountRoleImpl> roles = new HashSet<>();
        AccountRoleImpl role_user = (AccountRoleImpl)rolesService.findByName("ROLE_USER");
        roles.add(role_user);
        account.setAccountRoles(roles);

        minimessagesService.saveAccount(account);
        return "redirect:/accounts/" + account.getUsername();
    }

    /*When code was wrote field 'login' was named "username". In future i'm fix this */
    @RequestMapping(value = "/{username}",method = GET)
    public String showAccountProfiler(@PathVariable String username, Model model){
        UserAccount account = minimessagesService.getAccountByLogin(username);
        Set<Message> messages = account.getMessages();


        model.addAttribute("login", account.getUsername());
        model.addAttribute("messages", messages);
        return "accounts/view";
    }

    @RequestMapping(value = "/all",method = GET)
    public String showAllAccounts(Model model){
        List<UserAccount> accounts = minimessagesService.getAllAccounts();


        model.addAttribute("accounts", accounts);
        return "accounts/allAccounts";
    }

    /*@RequestMapping(value="/{login}", method=PUT)*/
    @RequestMapping(value="/subscribe_to/{login}", method=POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void subscribeToPublisherRESTstyle(@PathVariable("login") String publisher_account, Authentication authentication) {

        String current_account_login = authentication.getName();
        UserAccount subscriber = minimessagesService.getAccountByLogin(current_account_login);
        UserAccount publisher = minimessagesService.getAccountByLogin(publisher_account);

        if(publisher != null){
            subscriber.addPublisher(publisher);
            minimessagesService.update(subscriber);

            logger.debug("Was Executed method subscribeToPublisherRESTstyle (method=PUT) in class:"  + this.getClass().getName());
        }

        //return "accounts/" ;
    }

    /*@RequestMapping(value="/{login}", method=DELETE)*/
    @RequestMapping(value="/unsubscribe_from/{login}", method=POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unsubscribeFromPublisherRESTstyle(@PathVariable("login") String publisher_account, Authentication authentication) {

        String current_account_login = authentication.getName();
        UserAccount subscriber = minimessagesService.getAccountByLogin(current_account_login);
        UserAccount publisher = minimessagesService.getAccountByLogin(publisher_account);

        if(publisher != null){
            subscriber.removePublisher(publisher);
            minimessagesService.update(subscriber);

            logger.debug("Was Executed method unsubscribeFromPublisherRESTstyle (method=DELETE) in class:" + this.getClass().getName());
        }
    }

    @RequestMapping(value = "/news",method = GET)
    public String showPublishersMessages(Model model,Authentication authentication ){

        String current_account_login = authentication.getName();
        UserAccount current_account = minimessagesService.getAccountByLogin(current_account_login);

        Set<Message> publishers_messages = new HashSet<>();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date messages_created_after = calendar.getTime();

        // Collectin of last messages of publishers
        for(UserAccount account : current_account.getPublishers()){
            for(Message message: account.getMessages()){
                // add messages created after "n" days
                if(message.getCreated().after(messages_created_after)){
                    publishers_messages.add(message);
                }

            }
        }


        model.addAttribute("publishers_messages", publishers_messages);
        return "accounts/news";
    }
}
