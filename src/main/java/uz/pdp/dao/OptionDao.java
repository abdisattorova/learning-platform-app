package uz.pdp.dao;
//Sevinch Abdisattorova 02/18/2022 1:36 PM

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Option;
import uz.pdp.model.Task;
import uz.pdp.model.User;
import uz.pdp.model.UsersTasks;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OptionDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    TaskDao taskDao;

    public List<Option> getOptionsOfTask(int id) {
        Session session = entityManager.unwrap(Session.class);
        NativeQuery sqlQuery = session.createSQLQuery(
                "select * from options where task_id =" + id);
        sqlQuery.addEntity(Option.class);
        return (List<Option>) sqlQuery.list();
    }

    public void deleteOptionsOfTask(Integer id) {
        Session session = entityManager.unwrap(Session.class);
        NativeQuery sqlQuery = session.createSQLQuery(
                "delete  from options where task_id =" + id);
        sqlQuery.executeUpdate();
    }

    public void saveOptions(List<Option> options) {
        Session session = entityManager.unwrap(Session.class);
        for (Option option : options) {
            session.saveOrUpdate(option);
        }
    }

    public Option getOptionOfTask(int id) {
        Session session = entityManager.unwrap(Session.class);
        Option option = session.get(Option.class, id);
        return option;
    }

    public void writeCompletedTaskOfUser(Task taskById, User user) {
        Session session = entityManager.unwrap(Session.class);
        session.save(new UsersTasks(user, taskById, true));
    }
}
