package ua.golovchenko.artem.minimessage.mvc;

import org.springframework.security.core.userdetails.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import ua.golovchenko.artem.minimessage.model.Message;
import ua.golovchenko.artem.minimessage.model.UserAccount;

/**
 * Created by Artem on 19.04.2017.
 */
public class MessageBeanInterceptor implements WebRequestInterceptor {

    public void afterCompletion(WebRequest webRequest,
                                Exception arg1) throws Exception {
    }

    public void postHandle(WebRequest webRequest, ModelMap model)
            throws Exception {
        // TODO: Maybe should only do this if the user is logged in
        if(model != null) {
            model.addAttribute("message",new Message());
        }
    }

    public void preHandle(WebRequest webRequest) throws Exception {}

}
