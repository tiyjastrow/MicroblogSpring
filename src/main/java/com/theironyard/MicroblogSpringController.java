package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by WesleyLewis on 10/4/16.
 */
@Controller
public class MicroblogSpringController {
    static ArrayList<Message> messages = new ArrayList<>();
    int indexCount = 1;
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("messages", messages);
        return "home";
    }

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName){
        session.setAttribute("userName", userName);
        return "redirect:/";
    }
    @RequestMapping(path="/create-message", method=RequestMethod.POST)
    public String postMessage(HttpSession session, String text){
        Message m = new Message(returnIndex(), text);
        session.setAttribute("text", text);
        messages.add(m);
        return "redirect:/";

    }
    @RequestMapping(path="/delete-message", method = RequestMethod.POST)
    public String deleteMessage(int id){
        Message delete = new Message();
        for(Message m : messages){
            if(m.id == id){
                delete = m;
            }
        }
        messages.remove(delete);
        return "redirect:/";
    }
    public int returnIndex(){
        return indexCount++;
    }
}
