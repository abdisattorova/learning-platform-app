package uz.pdp.service;
//Sevinch Abdisattorova 02/13/2022 11:04 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.LessonDao;
import uz.pdp.model.Lesson;
import uz.pdp.model.Module;

import javax.transaction.Transactional;

@Service
public class LessonService {

    @Autowired
    LessonDao lessonDao;

    public Lesson getLessonById(int id) {
        return lessonDao.getLessonById(id);
    }
/*
    public String addModule(Lesson lesson) {
        if (lessonDao.addLesson(lesson) == 1) {
            return "Successfully added!";
        }
        return "Not added!";
    }*/

    @Transactional
    public void saveLesson(Lesson lesson) {
        lessonDao.saveCustomer(lesson);
    }

}
