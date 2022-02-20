package uz.pdp.dao;
//Sevinch Abdisattorova 02/18/2022 1:36 PM

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Discussion;

import java.util.List;

@Repository
public class DiscussionDao {

    @Autowired
    SessionFactory sessionFactory;


    public List<Discussion> getDiscussionsOfLesson(int lessonId) {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery sqlQuery = session.createSQLQuery(
                "select * from  discussions where lesson_id =" + lessonId);
        sqlQuery.addEntity(Discussion.class);
        List<Discussion> list = sqlQuery.list();
        return list;
    }

    public void addDiscussion(Discussion discussion) {
        Session session = sessionFactory.getCurrentSession();
        session.save(discussion);

    }
}
