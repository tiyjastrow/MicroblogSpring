package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class MicroblogSpringController {

    static ArrayList<Message> messages = new ArrayList<>();
    static int counter = 0;

    @RequestMapping(path="/", method= RequestMethod.GET)
    public String home(Model model, HttpSession session){
        model.addAttribute("name", session.getAttribute("userName"));
        model.addAttribute("messages", messages);
        return "home";
    }

    @RequestMapping(path="/login", method=RequestMethod.POST)
    public String login(HttpSession session, String userName){
        session.setAttribute("userName", userName);
        return "redirect:/";
    }

    @RequestMapping(path="/add-message", method=RequestMethod.POST)
    public String addMessage(String message){
        messages.add(new Message(counter, message));
        counter++;
        return "redirect:/";
    }

    @RequestMapping(path="delete-message", method=RequestMethod.POST)
    public String deleteMessage(Model model, int id){
        messages.removeIf(message-> id == message.id);

        return "redirect:/";
    }

}
