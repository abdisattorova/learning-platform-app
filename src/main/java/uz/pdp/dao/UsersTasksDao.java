package uz.pdp.dao;
//Sevinch Abdisattorova 02/20/2022 10:40 PM

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersTasksDao {
    @Autowired
    SessionFactory sessionFactory;

    public void deleteTaskFromUsersTask(int taskId) {
        Session currentSession = sessionFactory.getCurrentSession();
        NativeQuery nativeQuery = currentSession.createNativeQuery("delete from users_tasks where task_id=" + taskId);
        nativeQuery.executeUpdate();
    }
}
