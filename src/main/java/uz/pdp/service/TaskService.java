package uz.pdp.service;
//Sevinch Abdisattorova 02/18/2022 1:35 PM

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.LessonDao;
import uz.pdp.dao.OptionDao;
import uz.pdp.dao.TaskDao;
import uz.pdp.dao.UsersTasksDao;
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

    @Autowired
    UsersTasksDao usersTasksDao;

    @Transactional
    public TaskDto getTaskById(int id) {
        List<Option> options = optionDao.getOptionsOfTask(id);
        Task taskById = taskDao.getTaskById(id);
        return new TaskDto(taskById.getId(),
                taskById.getLesson().getId(),
                taskById.getLesson().getModule().getId(),
                taskById.getLesson().getName(),
                taskById.getBody(),
                taskById.getTitle(),
                null,
                false,
                options
        );
    }

    @Transactional
    public void saveTask(TaskDto taskDto, int correct_answer_flag) {
        if (taskDto.getId() != null) {
            usersTasksDao.deleteTaskFromUsersTask(taskDto.getId());
            taskDao.deleteTaskById(taskDto.getId());
            optionDao.deleteOptionsOfTask(taskDto.getId());
        }
        Lesson lessonById = lessonDao.getLessonById(taskDto.getLessonId());
        Task task = new Task(taskDto.getId(), lessonById, taskDto.getBody(), taskDto.getTitle());

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
    public List<TaskDto> getAllTasks(int lessonId, int userId) {
        List<Integer> completedTasksOfUser = taskDao.getCompletedTasksOfUser(userId);
        List<Task> allTasksOfLesson = taskDao.getAllTasksOfLesson(lessonId);
        List<TaskDto> taskDtoList = new ArrayList<>();
        for (Task task : allTasksOfLesson) {
            TaskDto taskDto = new TaskDto(task.getId(),
                    task.getLesson().getId(),
                    task.getLesson().getModule().getId(),
                    task.getLesson().getName(),
                    task.getBody(),
                    task.getTitle(),
                    null,
                    false,
                    optionDao.getOptionsOfTask(task.getId()));
            if (completedTasksOfUser.stream().anyMatch(integer -> {
                return task.getId().equals(integer);
            })) {
                taskDto.setIsCompleted(true);
            }

            taskDtoList.add(taskDto);
        }

        return taskDtoList;
    }


    @Transactional
    public void deleteTaskById(int id) {
        taskDao.deleteTaskById(id);
    }

}
