package uz.pdp.service;
//Sevinch Abdisattorova 02/18/2022 1:35 PM

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.LessonDao;
import uz.pdp.dao.OptionDao;
import uz.pdp.dao.TaskDao;
import uz.pdp.dto.TaskDto;
import uz.pdp.model.Lesson;
import uz.pdp.model.Option;
import uz.pdp.model.Task;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskDao taskDao;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    OptionDao optionDao;

    @Autowired
    LessonDao lessonDao;

    @Transactional
    public TaskDto getTaskById(int id) {
        List<Option> options = optionDao.getOptionsOfTask(id);
        Task taskById = taskDao.getTaskById(id);
        return new TaskDto(taskById.getId(),
                taskById.getLesson().getId(),
                taskById.getLesson().getName(),
                taskById.getBody(),
                taskById.getTitle(),
                null,
                options
        );
    }

    @Transactional
    public void saveTask(TaskDto taskDto, int correct_answer_flag) {

        if (taskDto.getId() != null) {
            taskDao.deleteTaskById(taskDto.getId());
            optionDao.deleteOptionsOfTask(taskDto.getId());
        }
        Lesson lessonById = lessonDao.getLessonById(taskDto.getLessonId());
        Task task = new Task(taskDto.getId(), lessonById, taskDto.getBody(),taskDto.getTitle());

        Task taskById = taskDao.getTaskById(taskDao.saveTask(task));
        List<String> answers = Arrays.asList(taskDto.getAnswers());

        List<Option> options = new ArrayList<>();
        for (int i = 1; i <= answers.size(); i++) {
            String answer = answers.get(i - 1);
            Option option = new Option(answer, taskById);
            if (i == correct_answer_flag) {
                option.setRightAnswer(true);
            } else {
                option.setRightAnswer(false);
            }
            options.add(option);
        }
        optionDao.saveOptions(options);
    }

    @Transactional
    public List<Task> getAllTasks(int lessonId) {
        return taskDao.getAllTasksOfLessson(lessonId);
    }
}
