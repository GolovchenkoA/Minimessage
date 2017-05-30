package ua.golovchenko.artem.minimessage.mvc;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.golovchenko.artem.minimessage.model.Message;
import ua.golovchenko.artem.minimessage.model.UserAccount;
import ua.golovchenko.artem.minimessage.service.MinimessagesService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.net.BindException;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Artem on 17.04.2017.
 */
@Controller
@RequestMapping("/messages")
public class MessageController {
    private MinimessagesService minimessagesService;

    @Inject
    public void setMinimessagesService(MinimessagesService minimessagesService) {
        this.minimessagesService = minimessagesService;
    }

    @RequestMapping(method = POST, headers = "Accept=text/html") /*RESTful style  */
    public String createMessageFromForm(@Valid Message message,BindingResult result, Authentication authentication)
            throws org.springframework.validation.BindException {

        if(result.hasErrors()){
            //throw new org.springframework.validation.BindException(result);
            return "redirect:/messages/MyMessages";
        }

        // prepare message before save
        String login = authentication.getName();
        UserAccount account = minimessagesService.getAccountByLogin(login);
        message.setAccount(account);
        message.setCreated(new Date());
        minimessagesService.saveMessage(message);

        return "redirect:/messages/MyMessages";
    }


    @RequestMapping(value="/MyMessages", method=GET)
    public String listMessagesForAccount(Authentication authentication, Model model){
        String login = authentication.getName();
        UserAccount account = minimessagesService.getAccountByLogin(login);
        List<Message> messages = minimessagesService.getMessagesForAccount(login);

        model.addAttribute("account",account);
        model.addAttribute("messagesList",messages);
        return "messages/list";
    }

}
