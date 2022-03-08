package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.RateDto;
import uz.pdp.model.Rate;
import uz.pdp.model.User;
import uz.pdp.service.CourseService;
import uz.pdp.service.UserService;
import uz.pdp.util.Constants;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static uz.pdp.util.Constants.getCourseWithImageUrl;

@Controller
@RequestMapping("/courses")
public class CourseController {


    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @GetMapping
    public String getAllCourses(Model model,
                                @RequestParam(name = "search",
                                        required = false,
                                        defaultValue = "0") String course,
                                HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<CourseDto> allCourses = null;
        if (course.equals("0")) {
            allCourses = courseService.getAllCourses();
        } else {
            allCourses = courseService.getCoursesBySearch(course);
            if (allCourses.size() == 0) {
                model.addAttribute("message", "Course not found!");
            }
        }
        for (CourseDto courseDto : allCourses) {
            courseDto.setAllTasksNum(courseService.countTasksOfCourse(courseDto.getId()));
            courseService.getRateOfCourse(courseDto);
            if (user != null) {
                courseDto.setSolvedTasksNum(courseService.countSolvedTasksOfCourseByUseer(user.getId(), courseDto.getId()));
                boolean b = courseService.checkIfUserIsMentorOfCourse(courseDto, user);
                courseDto.setIsUserAuthor(b);
            }
            getCourseWithImageUrl(courseDto);
        }
        model.addAttribute("user", user);
        model.addAttribute("courseList", allCourses);

        return "jsp/view-courses";


    }

    @GetMapping("/statistic")
    public String getAllStatistic(Model model) {
        int authors_count = courseService.getAllAuthors();
        int students_count = courseService.getAllStudents();
        int course_count = courseService.getAllCourseCount();
        int task_count = courseService.getAllTasks();

        List<CourseDto> courseStatistics = courseService.getStatisticsCourses();

        int sum = 0;
        for (CourseDto courseStatistic : courseStatistics) {
            sum += courseStatistic.getCount();
        }
        model.addAttribute("authors_count", authors_count);
        model.addAttribute("students_count", students_count);
        model.addAttribute("course_count", course_count);
        model.addAttribute("task_count", task_count);
        model.addAttribute("courseStatistics", courseStatistics);
        model.addAttribute("sum", sum);

        return "jsp/statistic-form";
    }

    @PostMapping
    public RedirectView addCourse(CourseDto courseDto,
                                  @RequestParam(name = "file", required = false) CommonsMultipartFile file) {
        String filename = file.getOriginalFilename();
        if (file != null && file.getOriginalFilename().endsWith(".jpg")
                || file.getOriginalFilename().endsWith(".png")) {

            byte[] bytes = file.getBytes();
            BufferedOutputStream stream = null;
            try {
                String imgPath = Constants.path + filename;
                courseDto.setImageUrl(filename);
                stream = new BufferedOutputStream(new FileOutputStream(
                        new File(imgPath)));

                stream.write(bytes);
                stream.flush();
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (courseDto.getImageUrl() != null && courseDto.getImageUrl().length() > 1) {
            String imageUrl = courseService.getCourseById(courseDto.getId()).getImageUrl();
            courseDto.setImageUrl(imageUrl);
        } else {
            courseDto.setImageUrl(null);
        }
        if (courseDto.getId() != 0) {
            courseService.editCourse(courseDto);
        } else {
            courseService.saveCourse(courseDto);
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/courses");
        return redirectView;
    }


    @GetMapping(path = "/info/{id}")
    public String showInfoAboutCourse(@PathVariable int id, Model model, HttpSession session) {
        CourseDto courseById = courseService.getCourseById(id);
        getCourseWithImageUrl(courseById);

        User user = (User) session.getAttribute("user");
        courseById.setAllTasksNum(courseService.countTasksOfCourse(id));
        if (user != null) {
            RateDto checkCourse = courseService.checkCourseRate(id, user.getId());
            courseById.setSolvedTasksNum(courseService.countSolvedTasksOfCourseByUseer(user.getId(), id));
            boolean result = courseService.checkIfUserIsMentorOfCourse(courseById, user);
            model.addAttribute("isAuthor", result);
            model.addAttribute("checkCourse", checkCourse);
        }
        model.addAttribute("user", user);
        model.addAttribute("course", courseById);
        return "jsp/course-info";
    }

    @GetMapping(path = "/form")
    public String getCourseForm(Model model, @RequestParam(name = "id", required = false, defaultValue = "0") int id) {
        List<User> authors = userService.getAllAuthors();
        System.out.println(authors);
        model.addAttribute("authors", authors);
        if (id == 0) return "jsp/course-form";
        CourseDto courseDto = courseService.getCourseById(id);
        if (courseDto != null) {
            model.addAttribute("selectedCourse", courseDto);
            return "jsp/course-form";
        } else {
            model.addAttribute("message", "Course not found!!");
            return "redirect:/courses";
        }

    }


    @GetMapping(path = "/delete/{id}")
    public String deleteCourseById(Model model, @PathVariable int id) {
        String res = courseService.deleteCourseById(id);
        model.addAttribute("message", res);
        return "redirect:/courses";
    }

    @GetMapping(path = "/rate/{courseId}")
    public String showRates(@PathVariable int courseId,
                            Model model) {
        CourseDto courseDto = courseService.getCourseById(courseId);
        model.addAttribute("course", courseDto);
        return "jsp/rate";
    }

    @PostMapping(path = "/rate-course/{courseId}")
    public String rateCourse(@PathVariable int courseId,
                             Model model,
                             Rate rate,
                             HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println(rate);
        CourseDto courseDto = courseService.getCourseById(courseId);
        model.addAttribute("course", courseDto);
        courseService.rateCourse(courseId, user, rate);
        List<CourseDto> allCourses = courseService.getAllCourses();
        model.addAttribute("courseList", allCourses);
        return "redirect:/courses";
    }


//    @GetMapping(path = "/certificate")
//    public String getCertificateOfUser(@RequestParam(name = "courseId") Integer courseId,
//                                       HttpSession session, Model model) {
//        User user = (User) session.getAttribute("user");
//        courseService.saveCertificate(courseId, user.getId());
//
//    }

}
