package uz.pdp.controller;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import uz.pdp.dto.CourseDto;
import uz.pdp.model.User;
import uz.pdp.service.CourseService;
import uz.pdp.service.UserService;
import uz.pdp.util.Constants;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import static uz.pdp.util.Constants.getCourseWithImageUrl;
import static uz.pdp.util.Constants.path;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    SessionFactory sessionFactory;

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
            if (user != null) {
                courseDto.setSolvedTasksNum(courseService.countSolvedTasksOfCourseByUseer(user.getId(), courseDto.getId()));
            }
            getCourseWithImageUrl(courseDto);
        }
       /* switch (user.getRole()) {
            case MENTOR:

        }*/

//        model.addAttribute("user",user);
        model.addAttribute("courseList", allCourses);

        return "view-courses";
    }

    @GetMapping("/statistic")
    public String getAllStatistic(Model model){
     int authors_count=   courseService.getAllAuthors();
     int students_count=courseService.getAllStudents();
     int course_count=courseService.getAllCourseCount();
     int task_count=courseService.getAllTasks();

     List<CourseDto> courseStatistics = courseService.getStatisticsCourses();

        int sum=0;
        for (CourseDto courseStatistic : courseStatistics) {
            sum+= courseStatistic.getCount();
        }
     model.addAttribute("authors_count",authors_count);
     model.addAttribute("students_count",students_count);
     model.addAttribute("course_count",course_count);
     model.addAttribute("task_count",task_count);
     model.addAttribute("courseStatistics",courseStatistics);
     model.addAttribute("sum",sum);

     return "statistic-form";
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
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path + courseById.getImageUrl()));

            ByteArrayOutputStream base = new ByteArrayOutputStream();
            ImageIO.write(image, "png", base);
            base.flush();
            byte[] imageInByteArray = base.toByteArray();
            base.close();

            String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);

            courseById.setImageUrl(b64);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();

        }

        User user =(User) session.getAttribute("user");

        courseById.setAllTasksNum(courseService.countTasksOfCourse(id));
        if (user != null) {
            courseById.setSolvedTasksNum(courseService.countSolvedTasksOfCourseByUseer(user.getId(), id));
        }

        model.addAttribute("course", courseById);
        return "course-info";
    }

    @GetMapping(path = "/form")
    public String getCourseForm(Model model, @RequestParam(name = "id", required = false, defaultValue = "0") int id) {
        List<User> authors = userService.getAllAuthors();
        System.out.println(authors);
        model.addAttribute("authors", authors);
        if (id == 0) return "course-form";
        CourseDto courseDto = courseService.getCourseById(id);
        if (courseDto != null) {
            model.addAttribute("selectedCourse", courseDto);
            return "course-form";
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





}
