package uz.pdp.controller;
//Sevinch Abdisattorova 02/13/2022 11:01 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import uz.pdp.dao.ModuleDao;
import uz.pdp.dto.CourseDto;
import uz.pdp.model.Lesson;
import uz.pdp.model.Module;
import uz.pdp.model.User;
import uz.pdp.service.LessonService;
import uz.pdp.service.ModuleService;

import java.util.List;

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


    /*@GetMapping(path = "/delete/{id}")
    public String deleteLessonById(Model model, @PathVariable int id) {
        String res = lessonService.deleteLessonById(id);
        model.addAttribute("message", res);
        return "redirect:/lessons";

    }*/

@GetMapping("/delete/{id}")
public String deleteLesson(@PathVariable int id){
    lessonService.deleteLessonById(id);
    return "redirect:/module";

}

}


