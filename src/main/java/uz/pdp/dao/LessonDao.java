package uz.pdp.dao;
//Sevinch Abdisattorova 02/13/2022 11:02 PM

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.dto.LessonDto;
import uz.pdp.model.Lesson;


@Repository
public class LessonDao {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    JdbcTemplate template;


    public Lesson getLessonById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Lesson lesson = session.get(Lesson.class, id);
        return lesson;
    }

    public void saveLesson(Lesson lesson) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(lesson);
    }
}
