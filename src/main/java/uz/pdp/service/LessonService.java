package uz.pdp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.LessonDao;
import uz.pdp.model.Lesson;

import javax.transaction.Transactional;

@Service
public class LessonService {

    @Autowired
    LessonDao lessonDao;

    public Lesson getLessonById(int id) {
        return lessonDao.getLessonById(id);
    }

    public void deleteLessonById(int theId) {
        lessonDao.deleteLesson(theId);
    }

    @Transactional
    public void saveLesson(Lesson lesson) {
        lessonDao.saveCustomer(lesson);

    }
}

