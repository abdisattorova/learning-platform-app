package uz.pdp.dao;
//Sevinch Abdisattorova 02/13/2022 11:02 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.model.Lesson;

@Component
public class LessonDao {

    @Autowired
    JdbcTemplate template;

    public Lesson getLessonById(int id) {
        // TODO: 02/13/2022
        return new Lesson();
    }
}
