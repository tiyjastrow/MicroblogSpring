package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class MicroblogSpringController {

    static String loggedInUser = "loggedInUser";
    static ArrayList<Message> messageList = new ArrayList<>();


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        model.addAttribute("name", session.getAttribute(loggedInUser));
        model.addAttribute("messageList", session.getAttribute("messageList"));
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {
        session.setAttribute(loggedInUser, userName);
        return "redirect:/";
    }

    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String addMessage(HttpSession session, String text) {
        Message m = new Message(messageList.size() + 1, text);
        messageList.add(m);
        session.setAttribute("messageList", messageList);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public String delete(HttpSession session, int id) {
        int tempId = messageList.get((id-1)).id ;
        messageList.remove(id-1);
        for (Message message : messageList) {
            if (message.id > tempId) {
                message.id = (message.id-1);
            }
        }

        session.setAttribute("messageList", messageList);
        return "redirect:/";
    }


}
