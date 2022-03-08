package uz.pdp.dao;
//Sevinch Abdisattorova 02/13/2022 11:02 PM

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Lesson;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class LessonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    JdbcTemplate template;

    public void deleteLesson(int theId) {
        Session session = entityManager.unwrap(Session.class);
        Lesson lesson = session.byId(Lesson.class).load(theId);
        session.delete(lesson);
    }

    public Lesson getLessonById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Lesson lesson = session.get(Lesson.class, id);
        return lesson;
    }

    public void saveLesson(Lesson lesson) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(lesson);

    }
}




