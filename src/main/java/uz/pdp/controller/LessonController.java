package uz.pdp.controller;
//Sevinch Abdisattorova 02/13/2022 11:01 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.dto.CourseDto;
import uz.pdp.model.Lesson;
import uz.pdp.service.CourseService;
import uz.pdp.service.LessonService;
import uz.pdp.service.ModuleService;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static uz.pdp.util.Constants.path;

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
}


