package uz.pdp.service;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.CertificateDao;
import uz.pdp.dao.CourseDao;
import uz.pdp.dao.TaskDao;
import uz.pdp.dao.UserDao;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.RateDto;
import uz.pdp.model.Certificate;
import uz.pdp.model.Course;
import uz.pdp.model.Rate;
import uz.pdp.model.User;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {


    @Autowired
    CourseDao courseDao;

    @Autowired
    TaskDao taskDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CertificateDao certificateDao;


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

    @Transactional
    public void rateCourse(int courseId, User user, Rate rate) {
        Course course = courseDao.getCourse(courseId);
        rate.setCourse(course);
        rate.setUser(user);
        courseDao.rateCourse(rate);

    }


    public RateDto checkCourseRate(int id, int user_id) {
        return courseDao.checkCourseRate(id, user_id);
    }

    @Transactional
    public void saveCertificate(int courseId, int userId) {
        int taskCount = taskDao.getTaskCount(courseId);
        int solvedTask = taskDao.getSolvedTask(userId, courseId);
        User userByIdFromDb = userDao.getUserByIdFromDb(userId);
        Course courseByIdFromDb = courseDao.getCourse(courseId);
        if (taskCount == solvedTask) {
            Certificate certificate = new Certificate();
            certificate.setUser(userByIdFromDb);
            certificate.setCourse(courseByIdFromDb);
            certificateDao.saveCertificate(certificate);
        }
    }

    @Transactional
    public void generateCertificate(int courseId, int userId) {
        if (certificateDao.getCertificateId(courseId, userId) != null) {
            Course courseByIdFromDb = courseDao.getCourse(courseId);
            User userByIdFromDb = userDao.getUserByIdFromDb(userId);
            try (PdfWriter pdfWriter = new PdfWriter("src/main/resources/certificate.pdf")) {

                PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                pdfDocument.addNewPage();
                Document document = new Document(pdfDocument);
                Paragraph paragraph = new Paragraph();
                paragraph.add("Certificate");
                Paragraph paragraph2 = new Paragraph();
                paragraph2.add(userByIdFromDb.getFullName());

                Paragraph paragraph3 = new Paragraph();
                paragraph3.add(courseByIdFromDb.getName());

                document.add(paragraph);
                document.add(paragraph2);
                document.add(paragraph3);
                document.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
