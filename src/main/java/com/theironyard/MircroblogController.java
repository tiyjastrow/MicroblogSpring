package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by joshuakeough on 10/4/16.
 */
@Controller
public class MircroblogController {
    ArrayList<Message> messages = new ArrayList<>();
    int counter=1;

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
    public String message(HttpSession session, String text) {
        Message message = new Message(counter, text);
        messages.add(message);
        counter++;
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String message(HttpSession session, Integer id) {
        Message deleteMe = new Message();
        for (Message m: messages) {
            if (m.getId()==id){
                deleteMe=m;
            }
        }
        messages.remove(deleteMe);

        return "redirect:/";
    }


}
