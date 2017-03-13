package com.theironyard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class MicroblogSpringController {
    String loggedInUser = "loggedInUser";
    ArrayList<Message> messages = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    //next create a call back method for the GET route
    public String home(Model model, HttpSession session) {
        model.addAttribute("name", session.getAttribute(loggedInUser));//reads the logged in user from the session into a local variable
        model.addAttribute("messages", messages);//in order to show the mustache
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)//this is the route
    public String login(HttpSession session, String userName){//the arguments for request and the userName
        session.setAttribute(loggedInUser, userName);
        return "redirect:/";
    }

    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String addMessage(Model model, String message){
        Message m = new Message(messages.size() + 1, message);
        messages.add(m);
        model.addAttribute("messages", m);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String deleteMessage(int id){
        int idNum = 0;
        for(Message message : messages){
            if(id == message.id){
                break;
            }
            idNum++;
        }
        messages.remove(idNum);
        return "redirect:/";
    }



}
