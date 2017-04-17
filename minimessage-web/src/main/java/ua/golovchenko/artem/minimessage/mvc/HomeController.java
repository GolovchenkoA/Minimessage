package ua.golovchenko.artem.minimessage.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.golovchenko.artem.minimessage.service.MinimessagesService;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by головченко on 16.03.2017.
 */

@Controller
public class HomeController{

    public static final int DEFAULT_MESSAGES_PER_PAGE = 25;

    private int messagesPerPage = DEFAULT_MESSAGES_PER_PAGE;

    private MinimessagesService service;

    @Inject
    public HomeController(MinimessagesService service) {
        this.service = service;
    }

    @RequestMapping(value = {"/","/home"},method = RequestMethod.GET)
    public String showHomePage(Map<String,Object> model){
        model.put("messages",service.getRecentMessages(messagesPerPage));
        return "home";
    }

    @RequestMapping(value = {"/welcome"},method = RequestMethod.GET)
    public String loginAccount(){
        return "welcome";
    }

        @RequestMapping(value = {"/logout"},method = RequestMethod.GET)
    public String logoutAccount(){
        return "j_spring_security_logout";
    }



    public void setMessagesPerPage(int messagesPerPage) {
        this.messagesPerPage = messagesPerPage;
    }

    public int getMessagesPerPage() {
        return messagesPerPage;
    }
}

