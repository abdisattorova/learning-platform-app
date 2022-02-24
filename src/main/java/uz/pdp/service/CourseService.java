package uz.pdp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.CourseDao;
import uz.pdp.dao.TaskDao;
import uz.pdp.dto.CourseDto;
import uz.pdp.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {


    @Autowired
    CourseDao courseDao;

    @Autowired
    TaskDao taskDao;

    public List<CourseDto> getAllCourses() {

        return courseDao.getCourses();
    }

    public void saveCourse(CourseDto courseDto) {
        courseDao.saveCourseToDb(courseDto);
    }

    public CourseDto getCourseById(int id) {
        if (id != 0) {
            CourseDto courseByIdFromDb = courseDao.getCourseByIdFromDb(id);
            if (courseByIdFromDb != null) return courseByIdFromDb;
        }
        return null;
    }

    public String deleteCourseById(int id) {
        int i = courseDao.deleteCourseByIdFromDb(id);
        if (i != 0) {
            return "Successfully deleted!!";
        } else return "Could not delete!!";
    }

    public void editCourse(CourseDto courseDto) {
        courseDao.editCourse(courseDto);

    }

    public List<CourseDto> getCoursesOfAuthor(int id) {
        List<CourseDto> courseDtoList = courseDao.getCoursesOfAuthor(id);
        return courseDtoList;
    }


    public List<CourseDto> getCoursesBySearch(String course) {
        List<CourseDto> allCourses = getAllCourses();
        return allCourses.stream().filter(courseDto -> courseDto.getName().toLowerCase()
                .contains(course.toLowerCase())).collect(Collectors.toList());
    }

    public int getAllAuthors() {
        return courseDao.getAllAuthors();
    }

    public int getAllStudents() {
        return courseDao.getAllStudent();
    }

    public int getAllCourseCount() {
        return courseDao.getAllCourseCount();

    }

    public int getAllTasks() {
        return courseDao.getAllTask();
    }

    public List<CourseDto> getStatisticsCourses() {


        return courseDao.getStatisticsCourses();

    }

    public int countTasksOfCourse(int id) {
        return taskDao.getTaskCount(id);
    }

    public int countSolvedTasksOfCourseByUseer(int userId, int courseId) {
        return taskDao.getSolvedTask(userId, courseId);
    }

    public int countAllCourses(int count) {
        return courseDao.countAllCourses();
    }

    public boolean checkIfUserIsMentorOfCourse(CourseDto courseById, User user) {
        return courseById.getAuthorDtoList().stream().anyMatch(authorDto -> authorDto.getId() == user.getId());
    }
}
