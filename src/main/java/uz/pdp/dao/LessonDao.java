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
    JdbcTemplate template;
    @Autowired
    SessionFactory sessionFactory;

    public void deleteLesson(int theId) {
        Session session = sessionFactory.getCurrentSession();
        Lesson lesson = session.byId(Lesson.class).load(theId);
        session.delete(lesson);
    }

    public Lesson getLessonById(int id) {
        // TODO: 02/13/2022
        return new Lesson();
    }



  /*  public int deleteLessonByIdFromDb(int id) {
        template.update(" delete  from lessons where id =" + id);
        return template.update("delete from lessons where id = " + id);
    }*/

//==============  ⏬

    public int deleteLessonByIdFromDb(int id) {
        try {
            String query = " delete  from lessons where id =" + id + " returning module_id  d";
            Integer integer = template.queryForObject(query, (rs, rowNum) -> {
                return rs.getInt(1);
            });
            return integer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}




