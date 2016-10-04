package com.theironyard;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.ArrayList;


/**
 * Created by joe on 04/10/2016.
 */
@Controller
public class MicroblogSpringController {

    ArrayList<Message> messages = new ArrayList<>();

    @RequestMapping(path="/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("messages", messages);
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName){
        session.setAttribute("userName", userName);
        return "redirect:/";
    }

    @RequestMapping(path="/add-message", method = RequestMethod.POST)
    public String addMessage(HttpSession session, String message){

        messages.add(new Message (messages.size()+1, message));
        session.setAttribute("messages", messages);
        return "redirect:/";
    }

    @RequestMapping(path ="/delete-message", method = RequestMethod.POST)
    public String deleteMessage(HttpSession session, int id){
        messages.remove(id-1);
        session.setAttribute("messages", messages);
        return "redirect:/";
    }
}
