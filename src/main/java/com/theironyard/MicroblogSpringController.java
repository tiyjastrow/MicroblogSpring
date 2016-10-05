package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by lee on 10/4/16.
 */
@Controller
public class MicroblogSpringController {
    ArrayList<Message> messages = new ArrayList<>();
    int lastCount = 1;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("messages", messages);
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {
        session.setAttribute("userName", userName);
        return "redirect:/";
    }

    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String addMessage(String messageText) {
        messages.add(new Message(returnNextId(), messageText));
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String deleteMessage(int id) {
        ArrayList<Message> toRemove = new ArrayList();
        for (Message m : messages) {
            if (m.id == id) {
                toRemove.add(m);
            }
        }
        messages.removeAll(toRemove);
        return "redirect:/";
    }

    public int returnNextId() {
        return lastCount++;
    }
}
