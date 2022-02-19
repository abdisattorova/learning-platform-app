package uz.pdp.dao;
//Sevinch Abdisattorova 02/18/2022 1:36 PM

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Lesson;
import uz.pdp.model.Option;

import java.util.List;

@Repository
public class OptionDao {
    @Autowired
    SessionFactory sessionFactory;

    public List<Option> getOptionsOfTask(int id) {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery sqlQuery = session.createSQLQuery(
                "select * from options where task_id =" + id);
        sqlQuery.addEntity(Option.class);
        return (List<Option>) sqlQuery.list();
    }

    public void deleteOptionsOfTask(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery sqlQuery = session.createSQLQuery(
                "delete  from options where task_id =" + id);
        sqlQuery.executeUpdate();
    }

    public void saveOptions(List<Option> options) {
        Session session = sessionFactory.getCurrentSession();
        for (Option option : options) {
            session.save(option);
        }
    }

    public Option getOptionOfTask(int answer) {
        Session session = sessionFactory.getCurrentSession();
        Option option = session.get(Option.class, answer);
        return option;
    }
}
