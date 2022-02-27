package uz.pdp.dao;
//Sevinch Abdisattorova 02/18/2022 1:36 PM

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Discussion;

import java.util.List;

@Repository
public class DiscussionDao {

    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    JdbcTemplate template;


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


    public int checkDiscuss(Discussion discussion) {
        try {
            String query = "select count(*)\n" +
                    "from restricted_words\n" +
                    "where word ilike '%" + discussion.getMessage() + "%';";
            Integer integer = template.queryForObject(query, (rs, rowNum) -> {
                return rs.getInt(1);
            });
            return integer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }


    public void changeBlockUser(int user_id) {
        String query = "UPDATE users\n" +
                "SET is_blocked=false\n" +
                "    WHERE id= " + user_id + ";";
        template.update(query);
    }
}
