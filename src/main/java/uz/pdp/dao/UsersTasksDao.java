package uz.pdp.dao;
//Sevinch Abdisattorova 02/20/2022 10:40 PM

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UsersTasksDao {


    @PersistenceContext
    private EntityManager entityManager;


    public void deleteTaskFromUsersTask(int taskId) {
        try {
            Session session = entityManager.unwrap(Session.class);
            NativeQuery nativeQuery = session.createNativeQuery("delete from users_tasks" +
                    " where task_id=" + taskId);
            nativeQuery.executeUpdate();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
