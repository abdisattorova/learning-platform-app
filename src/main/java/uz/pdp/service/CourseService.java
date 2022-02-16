package uz.pdp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.CourseDao;
import uz.pdp.dto.CourseDto;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class CourseService {


    @Autowired
    CourseDao courseDao;

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
}
