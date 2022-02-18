package uz.pdp.controller;
//Sevinch Abdisattorova 02/13/2022 11:01 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.Lesson;
import uz.pdp.service.CourseService;
import uz.pdp.service.LessonService;
import uz.pdp.service.ModuleService;


@Controller()
@RequestMapping("/lessons")

public class LessonController {
    @Autowired
    LessonService lessonService;

    @Autowired
    ModuleService moduleService;


    @Autowired
    CourseService courseService;


    @GetMapping(path = "/{id}")
    public String getLessonById(@PathVariable int id, Model model) {
        Lesson lessonById = lessonService.getLessonById(id);
        model.addAttribute("lesson", lessonById);
        return "/view-lesson";
    }

    @GetMapping(path = "/form")
    public String getLessonForm(Model model, @RequestParam(name = "id", required = false, defaultValue = "0") int id) {
        return "";
    }

    @PostMapping
    public void saveLesson(@ModelAttribute("Lesson") Lesson lesson) {
        lessonService.saveLesson(lesson);
    }

}


