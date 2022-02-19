package uz.pdp.controller;
//Sevinch Abdisattorova 02/18/2022 1:34 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.dto.TaskDto;
import uz.pdp.service.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping(path = "/form")
    public String getTaskForm(
            @RequestParam(name = "lessonId") int lessonId,
            Model model,
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
                           @RequestParam int correct_answer_flag
    ) {
        taskService.saveTask(taskDto, correct_answer_flag);
        return "";

    }
}

