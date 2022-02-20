package uz.pdp.controller;
//Sevinch Abdisattorova 02/20/2022 3:14 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.Discussion;
import uz.pdp.model.Lesson;
import uz.pdp.model.User;
import uz.pdp.service.DiscussionService;
import uz.pdp.service.LessonService;

import javax.servlet.http.HttpSession;
import java.util.List;

import static uz.pdp.util.Constants.getUserWithImageUrl;

@Controller
@RequestMapping("/discussions")
public class DiscussionController {

    @Autowired
    DiscussionService discussionService;

    @Autowired
    LessonService lessonService;

    @GetMapping("/{lessonId}")
    public String showDiscussionsOfLesson(
            @PathVariable("lessonId") int lessonId,
            HttpSession session,
            Model model
    ) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Discussion> discussions = discussionService.getDiscussionsOfLesson(lessonId);
            getUserWithImageUrl(user);
            model.addAttribute("user", user);
            model.addAttribute("discussions", discussions);
            return "view-discussions";
        }

        model.addAttribute("msg", "Please login first!");
        return "redirect:/login";

    }

    @PostMapping
    public String addMessageInDiscussion(
            Discussion discussion,
            HttpSession session,
            @RequestParam(name = "lessonId") int lessonId,
            Model model
    ) {
        User user = (User) session.getAttribute("user");
        Lesson lessonById = lessonService.getLessonById(lessonId);
        discussion.setLesson(lessonById);
        discussion.setUser(user);
        discussionService.addDiscussion(discussion);
        List<Discussion> discussions = discussionService.getDiscussionsOfLesson(lessonId);
        getUserWithImageUrl(user);
        model.addAttribute("user", user);
        model.addAttribute("discussions", discussions);
        return "view-discussions";
    }
}
