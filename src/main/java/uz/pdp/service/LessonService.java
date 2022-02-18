package uz.pdp.service;


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

   /* public String deleteLessonById(int id) {
        int i = lessonDao.deleteLessonByIdFromDb(id);
        if (i != 0) {
            return "Successfully deleted!!";
        } else return "Could not delete!!";
    }*/

    public void deleteLessonById(int theId) {
        lessonDao.deleteLesson(theId);
    }
}

