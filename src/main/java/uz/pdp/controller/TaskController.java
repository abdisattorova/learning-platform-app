package uz.pdp.controller;
//Sevinch Abdisattorova 02/18/2022 1:34 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.TaskDto;
import uz.pdp.model.Lesson;
import uz.pdp.model.User;
import uz.pdp.service.CourseService;
import uz.pdp.service.LessonService;
import uz.pdp.service.OptionService;
import uz.pdp.service.TaskService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @Autowired
    OptionService optionService;


    @Autowired
    LessonService lessonService;

    @GetMapping(path = "/form")
    public String getTaskForm(
            Model model,
            @RequestParam(name = "lessonId") int lessonId,
            @RequestParam(name = "id", required = false,
                    defaultValue = "0") int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("lessonId", lessonId);
        if (id != 0) {
            taskService.getTaskById(id, model, user);
//            model.addAttribute("task", task);
        }
        return "jsp/task-form";
    }

    @PostMapping
    public String saveTask(TaskDto taskDto,
                           @RequestParam int correct_answer_flag,
                           Model model, HttpSession session
    ) {
        User user = (User) session.getAttribute("user");
        taskService.saveTask(taskDto, correct_answer_flag);
        Lesson lessonById = lessonService.getLessonById(taskDto.getLessonId());
        model.addAttribute("lesson", lessonById);
        List<TaskDto> tasks;
        if (user != null) {
            tasks = taskService.getAllTasks(taskDto.getLessonId(), user.getId());
        } else {
            tasks = taskService.getAllTasks(taskDto.getLessonId(), 0);
        }
        model.addAttribute("tasks", tasks);
        return "jsp/view-lesson";
    }

    @GetMapping
    public String showTask(Model model,
                           @RequestParam(name = "id") int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        taskService.getTaskById(id, model, user);
        return "jsp/task-page";
    }

    @GetMapping("/check/{id}/{lessonId}")
    public String checkAnswer(@PathVariable(name = "id") int id,
                              @PathVariable(name = "lessonId") int lessonId,
                              @RequestParam(name = "answer") int answer,
                              Model model,
                              HttpSession session) {

        User user = (User) session.getAttribute("user");

        Lesson lessonById = lessonService.getLessonById(lessonId);
        model.addAttribute("lesson", lessonById);

        if (user != null) {
            if (optionService.checkAnswer(answer, id, user)) {
                model.addAttribute("msg", "Correct");
                List<TaskDto> tasks = taskService.getAllTasks(lessonId, user.getId());
                model.addAttribute("tasks", tasks);
                return "jsp/view-lesson";
            }
        } else {
            if (optionService.checkAnswer(answer)) {
                model.addAttribute("msg", "Correct");
                List<TaskDto> tasks = taskService.getAllTasks(lessonId, 0);
                model.addAttribute("tasks", tasks);
                return "redirect:/lessons/" + lessonId;
            }
        }

        model.addAttribute("msg", "Incorrect");
        taskService.getTaskById(id, model, user);
        return "jsp/task-page";
    }


    @GetMapping("/delete/{id}/{lessonId}")
    public String deleteTask(
            @PathVariable(name = "id") int id,
            @PathVariable(name = "lessonId") int lessonId,
            Model model,
            HttpSession session) {
        User user = (User) session.getAttribute("user");
        taskService.deleteTaskById(id);
        Lesson lessonById = lessonService.getLessonById(lessonId);
        List<TaskDto> tasks = taskService.getAllTasks(lessonId, user.getId());
        model.addAttribute("tasks", tasks);
        model.addAttribute("lesson", lessonById);
        return "jsp/view-lesson";
    }
}

