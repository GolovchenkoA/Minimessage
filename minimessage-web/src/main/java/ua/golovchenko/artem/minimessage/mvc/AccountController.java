package ua.golovchenko.artem.minimessage.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.golovchenko.artem.minimessage.model.Message;
import ua.golovchenko.artem.minimessage.model.UserAccount;
import ua.golovchenko.artem.minimessage.service.MinimessagesService;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.inject.Inject;
import java.util.Collections;
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
}
