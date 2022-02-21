package uz.pdp.service;
//Sevinch Abdisattorova 02/18/2022 1:35 PM

import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.OptionDao;
import uz.pdp.dao.TaskDao;
import uz.pdp.model.Option;
import uz.pdp.model.Task;
import uz.pdp.model.User;

import javax.transaction.Transactional;

@Service
public class OptionService {
    @Autowired
    OptionDao optionDao;

    @Autowired
    TaskDao taskDao;


    @Transactional
    public Boolean checkAnswer(int answer, int id, User user) {
        Option optionOfTask = optionDao.getOptionOfTask(answer);
        if (optionOfTask.getRightAnswer()) {
            optionDao.writeCompletedTaskOfUser(new Task(id), user);
        }
        return optionOfTask.getRightAnswer();

    }

    @Transactional
    public Boolean checkAnswer(int id) {
        Option optionOfTask = optionDao.getOptionOfTask(id);
        return optionOfTask.getRightAnswer();

    }
}
