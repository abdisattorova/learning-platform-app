package uz.pdp.dao;
//Sevinch Abdisattorova 02/18/2022 1:36 PM

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Task;

import java.util.List;

@Repository
public class TaskDao {

    @Autowired
    SessionFactory sessionFactory;


    public Task getTaskById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.get(Task.class, id);
        return task;
    }

    public Integer saveTask(Task task) {
        Session currentSession = sessionFactory.getCurrentSession();
        Integer id = (Integer) currentSession.save(task);
        return id;
    }

    public void deleteTaskById(Integer id) {

        Session session = sessionFactory.getCurrentSession();
        Task task = session.byId(Task.class).load(id);
        session.delete(task);
    }

    public List<Task> getAllTasks() {
        Session session = sessionFactory.getCurrentSession();
        List<Task> tasks = (List<Task>) session.createQuery("from Task").list();

        return tasks;

    }

    public List<Task> getAllTasksOfLessson(int lessonId) {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery("select * from tasks where lesson_id =" + lessonId);
        query.addEntity(Task.class);
        List<Task> tasks = (List<Task>) query.list();
        return tasks;

    }
}
