package uz.pdp.controller;
//Sevinch Abdisattorova 02/13/2022 11:01 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.Lesson;
import uz.pdp.service.LessonService;
import uz.pdp.service.ModuleService;

@Controller("/lessons")
public class LessonController {
    @Autowired
    LessonService lessonService;

    @Autowired
    ModuleService moduleService;

    @GetMapping(path = "/{id}")
    public Lesson getLessonById(@PathVariable int id) {
        return lessonService.getLessonById(id);

    }

    @GetMapping(path = "/form")
    public String getLessonForm(Model model, @RequestParam(name = "id", required = false, defaultValue = "0") int id) {
        return "";
    }


    @GetMapping("/delete/{id}")
    public String deleteLesson(@PathVariable int id) {
        lessonService.deleteLessonById(id);
        return "redirect:/module";

    }

    @PostMapping
    public void saveLesson(@ModelAttribute("Lesson") Lesson lesson) {
        lessonService.saveLesson(lesson);
    }


}


