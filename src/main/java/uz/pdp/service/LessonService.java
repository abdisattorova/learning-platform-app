package uz.pdp.service;
//Sevinch Abdisattorova 02/13/2022 11:04 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.LessonDao;
import uz.pdp.model.Lesson;

@Service
public class LessonService {

    @Autowired
    LessonDao lessonDao;

    public Lesson getLessonById(int id) {
        return lessonDao.getLessonById(id);
    }
}
