package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by jeremypitt on 10/4/16.
 */
@Controller
public class MircroBlogSpringController {
    static ArrayList<Message> messages = new ArrayList<>();
    int counter = 1;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String user(Model model, HttpSession session) {
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
    public String addMessage(String message) {
        Message m = new Message(returnIndex(), message);
        messages.add(m);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String deleteMessage(int delID) {
        Message deleteMe = new Message();
        for(Message m: messages){
            if (m.id == delID) {
                deleteMe = m;
            }
        }
        messages.remove(deleteMe);
        return "redirect:/";
    }

    public int returnIndex(){
        return counter++;
    }
}
