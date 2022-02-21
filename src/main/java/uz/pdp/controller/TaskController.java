package uz.pdp.controller;
//Sevinch Abdisattorova 02/18/2022 1:34 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.TaskDto;
import uz.pdp.model.Lesson;
import uz.pdp.model.Task;
import uz.pdp.model.User;
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
                    defaultValue = "0") int id) {
        model.addAttribute("lessonId", lessonId);
        if (id != 0) {
            TaskDto task = taskService.getTaskById(id);
            model.addAttribute("task", task);
        }
        return "task-form";
    }

    @PostMapping
    public String saveTask(TaskDto taskDto,
                           @RequestParam int correct_answer_flag,
                           Model model, HttpSession session
    ) {
        User user = (User) session.getAttribute("user");
        taskService.saveTask(taskDto, correct_answer_flag);
        Lesson lessonById = lessonService.getLessonById(taskDto.getLessonId());
        List<TaskDto> tasks = taskService.getAllTasks(taskDto.getLessonId(), user.getId());
        model.addAttribute("tasks", tasks);
        model.addAttribute("lesson", lessonById);
        return "view-lesson";

    }

    @GetMapping
    public String showTask(Model model,
                           @RequestParam(name = "id") int id) {
        TaskDto taskDto = taskService.getTaskById(id);
        model.addAttribute("task", taskDto);

        return "task-page";
    }

    @GetMapping("/check/{id}/{lessonId}")
    public String checkAnswer(@PathVariable(name = "id") int id,
                              @PathVariable(name = "lessonId") int lessonId,
                              @RequestParam(name = "answer") int answer,
                              Model model,
                              HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user != null) {
            if (optionService.checkAnswer(answer, id, user)) {
                Lesson lessonById = lessonService.getLessonById(lessonId);
                List<TaskDto> tasks = taskService.getAllTasks(lessonId, user.getId());
                model.addAttribute("tasks", tasks);
                model.addAttribute("lesson", lessonById);
                return "view-lesson";
            }
        } else {
            if (optionService.checkAnswer(answer, id)) {
                List<TaskDto> tasks = taskService.getAllTasks(lessonId,0);
                model.addAttribute("tasks", tasks);
                Lesson lessonById = lessonService.getLessonById(lessonId);
                model.addAttribute("lesson", lessonById);
                return "view-lesson";
            }
        }

        model.addAttribute("msg", "Incorrect");
        TaskDto taskDto = taskService.getTaskById(id);
        model.addAttribute("task", taskDto);
        return "task-page";
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
        return "view-lesson";
    }
}

