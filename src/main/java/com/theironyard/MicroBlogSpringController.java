package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by EdHall on 10/4/16.
 */


@Controller
public class MicroBlogSpringController {
    ArrayList<Message> messages = new ArrayList<>();
    int lastCount = 1;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        model.addAttribute("name", session.getAttribute("userName"));
        model.addAttribute("messages", messages);
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {
        session.setAttribute("userName", userName);
        return "redirect:/";
    }

    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String message(String text) {
        Message m = new Message(returnLastCount(), text);
        messages.add(m);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String delete(int id) {
        Message msg = new Message();

        for (Message m : messages) {
            if (id == m.id) {
                msg = m;
            }
        }
        messages.remove(msg);
        return "redirect:/";
    }

    public int returnLastCount() {
        return lastCount++;
    }
}