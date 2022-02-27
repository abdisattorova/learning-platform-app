package uz.pdp.controller;
//Sevinch Abdisattorova 02/26/2022 11:57 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.model.User;
import uz.pdp.service.MessageService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "messages")
public class MessageController {

    @Autowired
    MessageService messageService;


    @GetMapping(path = "/contact-with-admin")
    public String promoteToMentor(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        messageService.contactWithAdmin(model, user);
        return "chat";

    }

    @GetMapping(path = "/{receiverId}")
    public String getMessagingPeople(@PathVariable(name = "receiverId") Integer receiverId, Model model, HttpSession session) {
        messageService.getMessagingPeopleOfUser(receiverId, model);
        return "chat-people";
    }

    @GetMapping(path = "/with/{personId}")
    public String getMessagesWithPerson(HttpSession session, @PathVariable Integer personId, Model model) {
        User user = (User) session.getAttribute("user");
        messageService.getMessagesWithPerson(personId, user, model);
        return "chat";
    }

}
