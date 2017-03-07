package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class SpringController {

    public static ArrayList<Message> messages = new ArrayList<>();
    public static Integer uniqueId = 0;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        model.addAttribute("name", session.getAttribute("username"));
        model.addAttribute("Messages", session.getAttribute("messages"));
        return "home";
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName){
        session.setAttribute("username", userName);
        return "redirect:/";
    }
    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String addMessage(HttpSession session, String msgText){
        Message msg = new Message(msgText);
        messages.add(msg);
        session.setAttribute("messages", messages);
        return "redirect:/";
    }
    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String deleteMessage(HttpSession session, Integer DeleteIndex){
        session.setAttribute("Id", DeleteIndex);
        messages.removeIf(message -> message.id==DeleteIndex);
        return "redirect:/";
    }
}
