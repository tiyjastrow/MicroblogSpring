package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class MicroblogSpringController {
    private static String loggedInUser = "loggedInUser";
    ArrayList<Message> messages = new ArrayList<>();


    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String add(String text) {
        Message msg = new Message(messages.size() + 1, text);
        messages.add(msg);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String delete(int id) {
            messages.remove(id - 1);

        return "redirect:/";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        model.addAttribute("name", session.getAttribute(loggedInUser));
        model.addAttribute("messages", messages);
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {
        session.setAttribute(loggedInUser, userName);
        return "redirect:/";
    }



}


