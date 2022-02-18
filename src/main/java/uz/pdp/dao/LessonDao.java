package uz.pdp.dao;
//Sevinch Abdisattorova 02/13/2022 11:02 PM

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Lesson;

@Component
public class LessonDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    JdbcTemplate template;

    public void deleteLesson(int theId) {
        Session session = sessionFactory.getCurrentSession();
        Lesson lesson = session.byId(Lesson.class).load(theId);
        session.delete(lesson);
    }

    public Lesson getLessonById(int id) {
        // TODO: 02/13/2022
        return new Lesson();
    }



    public void saveCustomer(Lesson lesson) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(lesson);

    }
}




