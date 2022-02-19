package uz.pdp.controller;
//Sevinch Abdisattorova 02/18/2022 1:34 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.ModuleDto;
import uz.pdp.dto.TaskDto;
import uz.pdp.model.Task;
import uz.pdp.service.OptionService;
import uz.pdp.service.TaskService;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @Autowired
    OptionService optionService;

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

    @GetMapping
    public String showTask(Model model,
                           @RequestParam(name = "id") int id) {
        TaskDto taskDto = taskService.getTaskById(id);
        model.addAttribute("task", taskDto);

        return "task-page";
    }

    @GetMapping("/check/{id}")
    public String checkAnswer(@RequestParam(name = "answer") int answer,
                              @PathVariable(name = "id") int id) {
      String resu =  optionService.checkAnswer(answer);

        return "";
    }
}

