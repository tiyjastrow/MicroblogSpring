package com.theironyard;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by ericcollier on 10/4/16.
 */

@Controller
public class MicroblogSpringController {

    static ArrayList<Message> messages = new ArrayList<>();
    int indexCounter = 1;

    @RequestMapping(path="/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        model.addAttribute("name", session.getAttribute("userName"));
        model.addAttribute("messages", messages);
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName){
        session.setAttribute("userName", userName);
        return "redirect:/";
    }

    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String addMessage(String message){
        Message m = new Message(returnIndex(), message );
        messages.add(m);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
        public String deleteMessage(int id){
        Message deleteMessage = new Message();
        for (Message m:messages) {
            if(m.id == id){
                deleteMessage = m;
            }
        }
        messages.remove(deleteMessage);
        return "redirect:/";
    }

    public int returnIndex(){
        return indexCounter++;
    }

}
