package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by jakefroeb on 10/7/16.
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
    @RequestMapping(path="/login", method = RequestMethod.POST)
    public String login(HttpSession session, String loginName){
        session.setAttribute("userName", loginName);
        return "redirect:/";
    }
    @RequestMapping(path="/create-message", method = RequestMethod.POST)
    public String addMessage(String text){
        int id = messages.size()+1;
        Message message = new Message(id, text);
        messages.add(message);
        return "redirect:/";
    }
    @RequestMapping(path="/delete-message", method = RequestMethod.POST)
    public String deleteMessage(int id){
        messages.remove(id-1);
        return "redirect:./";
    }
}