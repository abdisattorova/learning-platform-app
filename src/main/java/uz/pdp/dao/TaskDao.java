package uz.pdp.dao;
//Sevinch Abdisattorova 02/18/2022 1:36 PM

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Task;

import java.util.List;

@Repository
public class TaskDao {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    JdbcTemplate template;

    public Task getTaskById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.get(Task.class, id);
        return task;
    }

    public void saveTask(Task task) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(task);
    }

    public void deleteTaskById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.byId(Task.class).load(id);
        session.delete(task);
    }

    public List<Task> getAllTasksOfLesson(int lessonId) {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery("select * from tasks where lesson_id =" + lessonId);
        query.addEntity(Task.class);
        List<Task> tasks = (List<Task>) query.list();
        return tasks;

    }

    public List<Integer> getCompletedTasksOfUser(int userId) {
        Session currentSession = sessionFactory.getCurrentSession();
        NativeQuery query = currentSession.createNativeQuery("select distinct task_id from users_tasks where user_id =" + userId);

        return query.list();

    }

    public int getTaskCount(int id) {
        String query = "select * from countAllTasksOfCourse(" + id + ")";
        Integer integer1 = template.queryForObject(query, (rs, rowNum) -> {
            return rs.getInt(1);
        });
        return integer1;
    }

    public int getSolvedTask(int userId, int courseId) {
        String query = "select * from countsolvedtasksofuserbycourse" +
                "(" + userId + "," + courseId + ")";
        Integer integer1 = template.queryForObject(query, (rs, rowNum) -> {
            return rs.getInt(1);
        });
        return integer1;
    }
}
