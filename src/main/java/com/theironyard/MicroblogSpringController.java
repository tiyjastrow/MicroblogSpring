package com.theironyard;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class MicroblogSpringController {

    public static ArrayList<Message> messages = new ArrayList<>();

    @RequestMapping(path ="/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("messages", messages);
        return "home";
    }

    @RequestMapping(path ="/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {
        if (userName == null) {
            Person p = new Person();
            p.userName = userName;
        }
        session.setAttribute("userName", userName);
        return "redirect:/";
    }

    @RequestMapping(path ="/add-message", method = RequestMethod.POST)
    public String addMessage(String text) {
        int id = (messages.size()+1);
        Message m = new Message(id, text);
        messages.add(m);
        return "redirect:/";
    }

    @RequestMapping(path ="/delete-message", method = RequestMethod.POST)
    public String deleteMessage(Integer id) {
        messages.remove(id-1);
        return "redirect:/";
    }




}
