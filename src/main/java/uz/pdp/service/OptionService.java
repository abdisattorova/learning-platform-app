package uz.pdp.service;
//Sevinch Abdisattorova 02/18/2022 1:35 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.OptionDao;
import uz.pdp.dao.TaskDao;
import uz.pdp.model.Option;

import javax.transaction.Transactional;

@Service
public class OptionService {
    @Autowired
    OptionDao optionDao;

    @Transactional
    public String checkAnswer(int answer) {
        Option optionOfTask = optionDao.getOptionOfTask(answer);
        if (optionOfTask.getRightAnswer()) {
            return "Correct!";
        } else return "Incorrect!";
    }
}
