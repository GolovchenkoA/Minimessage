package ua.golovchenko.artem.minimessage.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.golovchenko.artem.minimessage.model.Message;
import ua.golovchenko.artem.minimessage.service.MinimessagesService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.net.BindException;

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

    @RequestMapping(method = POST, headers = "Accept=text/html")
    public String createMessageFromForm(@Valid Message message,BindingResult result, HttpServletResponse response)
            throws org.springframework.validation.BindException {

        if(result.hasErrors()){
            throw new org.springframework.validation.BindException(result);
        }

        minimessagesService.saveMessage(message);
        return "redirect:/";
    }

}
