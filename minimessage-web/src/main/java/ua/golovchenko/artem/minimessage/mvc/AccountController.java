package ua.golovchenko.artem.minimessage.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.golovchenko.artem.minimessage.model.Message;
import ua.golovchenko.artem.minimessage.model.UserAccount;
import ua.golovchenko.artem.minimessage.service.MinimessagesService;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Artem on 14.04.2017.
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final MinimessagesService minimessagesService;

    @Inject
    public AccountController(MinimessagesService minimessagesService) {
        this.minimessagesService = minimessagesService;
    }

    @RequestMapping(value="/messages", method=GET)
    public String listMessagesForAccount(@RequestParam("account") String login, Model model){
        UserAccount account = minimessagesService.getAccountByLogin(login);
        List<Message> messages = minimessagesService.getMessagesForAccount(login);

        model.addAttribute("account",account);
        model.addAttribute("messagesList",messages);
        return "messages/list";
    }

    @RequestMapping(params = "new", method = GET)
    public String createAccountProfiler(Model model){
        model.addAttribute("account", new UserAccount());
        return "accounts/edit";
    }


    @RequestMapping(method = POST)
    /*public String addAccountFromForm(@Valid @ModelAttribute("newAccount") UserAccount account, BindingResult bindingResult){*/
    public String addAccountFromForm(@Valid UserAccount account, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "accounts/edit";
        }

        account.setCreated(new Date());
        account.setEnabled(true);
        minimessagesService.saveAccount(account);
        return "redirect:/accounts/" + account.getUsername();
    }

    /*When code was wrote field 'login' was named "username". In future i'm fix this */
    @RequestMapping(value = "/{username}",method = GET)
    public String showAccountProfiler(@PathVariable String username, Model model){
        model.addAttribute("account", minimessagesService.getAccountByLogin(username));
        return "accounts/view";
    }

}
