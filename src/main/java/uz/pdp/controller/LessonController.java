package uz.pdp.controller;
//Sevinch Abdisattorova 02/13/2022 11:01 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.LessonDto;
import uz.pdp.dto.ModuleDto;
import uz.pdp.dto.TaskDto;
import uz.pdp.model.Lesson;
import uz.pdp.model.User;
import uz.pdp.service.CourseService;
import uz.pdp.service.LessonService;
import uz.pdp.service.ModuleService;
import uz.pdp.service.TaskService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static uz.pdp.util.Constants.path;

@Controller("/lessons")
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    LessonService lessonService;

    @Autowired
    ModuleService moduleService;


    @Autowired
    CourseService courseService;

    @Autowired
    TaskService taskService;


    @GetMapping(path = "/{id}")
    public String getLessonById(@PathVariable int id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Lesson lessonById = lessonService.getLessonById(id);
        List<TaskDto> tasks = new ArrayList<>();
        if (user != null) {
            tasks = taskService.getAllTasks(id, user.getId());
            Integer courseId = lessonById.getModule().getCourse().getId();
            CourseDto courseById = courseService.getCourseById(courseId);
            boolean b = courseService.checkIfUserIsMentorOfCourse(courseById, user);
            model.addAttribute("isAuthor", b);
        } else {
            tasks = taskService.getAllTasks(id, 0);
        }
        model.addAttribute("lesson", lessonById);
        model.addAttribute("tasks", tasks);
        return "jsp/view-lesson";
    }


    @GetMapping(path = "/form")
    public String getLessonForm(Model model,
                                @RequestParam(name = "id", required = false,
                                        defaultValue = "0") int id,
                                @RequestParam(name = "moduleId") int moduleId) {
        if (id != 0) {
            Lesson lessonById = lessonService.getLessonById(id);
            model.addAttribute("lesson", lessonById);
        }
        model.addAttribute("moduleId", moduleId);
        return "jsp/lesson-form";
    }


    @GetMapping("/delete/{id}/{courseId}")
    public String deleteLesson(@PathVariable(name = "id") int id,
                               @PathVariable("courseId") int courseId,
                               Model model) {
        lessonService.deleteLessonById(id);

        CourseDto courseById = courseService.getCourseById(courseId);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path + courseById.getImageUrl()));

            ByteArrayOutputStream base = new ByteArrayOutputStream();
            ImageIO.write(image, "png", base);
            base.flush();
            byte[] imageInByteArray = base.toByteArray();
            base.close();

            String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);

            courseById.setImageUrl(b64);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();

        }
        model.addAttribute("course", courseById);
        return "redirect:/courses/info/"+courseId;


    }

    @PostMapping
    public String saveLesson(@ModelAttribute("lesson") LessonDto lesson,
                             @RequestParam(name = "moduleId") int moduleId,
                             Model model) {
        lessonService.saveLesson(lesson, moduleId);
        ModuleDto moduleById = moduleService.getModuleById(moduleId);
        Integer courseId = moduleById.getCourseId();
        CourseDto courseById = courseService.getCourseById(courseId);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path + courseById.getImageUrl()));

            ByteArrayOutputStream base = new ByteArrayOutputStream();
            ImageIO.write(image, "png", base);
            base.flush();
            byte[] imageInByteArray = base.toByteArray();
            base.close();

            String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);

            courseById.setImageUrl(b64);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();

        }
        model.addAttribute("course", courseById);
        return "redirect:/courses/info/"+courseId;

    }


}


