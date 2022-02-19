package uz.pdp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.LessonDao;
import uz.pdp.dao.ModuleDao;
import uz.pdp.dto.LessonDto;
import uz.pdp.dto.ModuleDto;
import uz.pdp.model.Lesson;
import uz.pdp.model.Module;

import javax.transaction.Transactional;

@Service
public class LessonService {

    @Autowired
    LessonDao lessonDao;

    @Autowired
    ModuleDao moduleDao;


    @Transactional
    public Lesson getLessonById(int id) {
        return lessonDao.getLessonById(id);
    }

    @Transactional
    public void saveLesson(LessonDto lesson, int moduleId) {
        Module module = moduleDao.getModule(moduleId);
        lessonDao.saveLesson(new Lesson(lesson.getId(),
                lesson.getName(),
                module,
                lesson.getBody(),
                lesson.getVideo_link()));
    }

    @Transactional
    public void deleteLessonById(int theId) {
        lessonDao.deleteLesson(theId);
    }


}

