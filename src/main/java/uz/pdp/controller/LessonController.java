package uz.pdp.controller;
//Sevinch Abdisattorova 02/13/2022 11:01 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.model.Lesson;
import uz.pdp.service.LessonService;

@Controller
public class LessonController {
    @Autowired
    LessonService lessonService;

    @RequestMapping(path = "/lessons/{id}")
    public Lesson getLessonById(@PathVariable int id){

       return lessonService.getLessonById(id);
    }
}
