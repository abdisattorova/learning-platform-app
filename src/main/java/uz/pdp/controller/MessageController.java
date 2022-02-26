package uz.pdp.controller;
//Sevinch Abdisattorova 02/26/2022 11:57 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.User;
import uz.pdp.service.MessageService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping(path = "/askPromotion/{userId}")
    public String promoteToMentor(Model model, @PathVariable(name = "userId") Integer userId) {
        messageService.promoteToAdmin(model, userId);
        return "redirect:/courses";
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
