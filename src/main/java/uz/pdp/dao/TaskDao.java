package uz.pdp.dao;
//Sevinch Abdisattorova 02/18/2022 1:36 PM

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.dto.TaskDto;
import uz.pdp.model.Lesson;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDao {

    @Autowired
    SessionFactory sessionFactory;

    public List<TaskDto> getTasksOfLesson(Integer id) {
        return new ArrayList<>();
    }
}
