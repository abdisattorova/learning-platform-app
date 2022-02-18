package uz.pdp.dao;
//Sevinch Abdisattorova 02/13/2022 11:02 PM

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.model.Lesson;

@Component
public class LessonDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    JdbcTemplate template;

    public Lesson getLessonById(int id) {
        // TODO: 02/13/2022
        return new Lesson();
    }
/*    public int addLesson(Lesson lesson) {
        try {
            String query = "insert into lessons (name, module_id,body,video_link) " +
                    "values ('" + lesson.getName() + "'," + lesson.getModule().getId() + ","+lesson.getBody()+","+lesson.getVideo_link()+");";
            int update = template.update(query);
            return update;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }*/
    public void saveCustomer(Lesson lesson) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(lesson);
    }
}
