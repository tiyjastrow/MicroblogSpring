package com.theIronYard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by halleyfroeb on 10/5/16.
 */
@Controller
public class MicroblogSpringController {
    ArrayList<Message> messages = new ArrayList<>();
    @RequestMapping(path="/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        model.addAttribute("name", session.getAttribute("userName"));
        model.addAttribute("messages", messages);
        return "home";
    }
    @RequestMapping(path="/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName){
        session.setAttribute("userName", userName);
        return "redirect:/";
    }
    @RequestMapping(path="/add-message", method = RequestMethod.POST)
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
    @RequestMapping(path="/logout", method = RequestMethod.POST)
    public String logout(HttpSession session, String userName) {
        session.invalidate();
        return "redirect:./";
    }
}
