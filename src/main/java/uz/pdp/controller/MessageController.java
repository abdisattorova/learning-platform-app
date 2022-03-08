package uz.pdp.controller;
//Sevinch Abdisattorova 02/26/2022 11:57 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.Message;
import uz.pdp.model.User;
import uz.pdp.service.MessageService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/messages")
public class MessageController {

    @Autowired
    MessageService messageService;


    @GetMapping(path = "/contact-with-admin")
    public String promoteToMentor(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        messageService.contactWithAdmin(model, user);
        return "jsp/chat";

    }


    @GetMapping(path = "/{receiverId}")
    public String getMessagingPeople(@PathVariable(name = "receiverId") Integer receiverId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        messageService.getMessagingPeopleOfUser(receiverId, model);
        return "jsp/chat-people";
    }


    @GetMapping(path = "/with/{personId}")
    public String getMessagesWithPerson(HttpSession session, @PathVariable Integer personId, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("msg", "Please login first!");
            return "jsp/login";
        }
        messageService.getMessagesWithPerson(personId, user, model);
        return "jsp/chat";
    }


    @PostMapping()
    public String saveMessage(@RequestParam(name = "personId") Integer personId, HttpSession session, Model model, Message message) {
        User user = (User) session.getAttribute("user");
        messageService.saveMessage(user, personId, model, message);
        messageService.getMessagesWithPerson(personId, user, model);
        return "jsp/chat";
    }

    @GetMapping(path = "/users/search")
    public String searchUsers(@RequestParam(name = "search") String search, Model model) {
        messageService.getUsersBySearch(search, model);
        return "jsp/chat-people";
    }

}
