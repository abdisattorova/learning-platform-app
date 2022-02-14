package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import uz.pdp.dto.CourseDto;
import uz.pdp.model.User;
import uz.pdp.service.CourseService;
import uz.pdp.service.UserService;
import uz.pdp.util.Constants;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @RequestMapping(path = "/login")
    public String showLoginForm() {
        return "login";
    }


    @RequestMapping(path = "/register")
    public String showRegisterForm() {
        return "register";
    }


    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public String getAllUsers(Model model, @RequestParam(defaultValue = "1") Integer page) {
        List<User> allUsers = userService.getAllUsers(page);
        int count = userService.countAllUsers();
        int pages = count / Constants.number_of_elements_in_1_page; //count 10  10/3=3
        int remainder = count % Constants.number_of_elements_in_1_page; //10    10%3=1

        if (remainder > 0) {
            pages = pages + 1;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("userList", allUsers);
        return "view-users";

    }


    @RequestMapping(path = "/users/login", method = RequestMethod.POST)
    public String authUser(User user, Model model) {
        String password = user.getPassword();
        String username = user.getUsername();
        User userFromDb = userService.getUserByUsernamePassword(username, password);
        if (userFromDb != null) {
            List<CourseDto> allCourses = courseService.getAllCourses();
            model.addAttribute("user", userFromDb);
            model.addAttribute("courseList", allCourses);
            return "view-courses";
//            return "navbar";
        }
        model.addAttribute("msg", "Username or password is incorrect!");
        return "/login";

    }


    @RequestMapping("/users/form")
    public String getUserForm(Model model, @RequestParam(name = "id", required = false, defaultValue = "0") int id) {
        if (id == 0) return "user-form";
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("selectedUser", user);
            return "user-form";
        } else {
            model.addAttribute("message",
                    "User not found!!");
            return "redirect:/users";
        }
    }


    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model) {
        if (user.getId() != 0) {
            userService.editUser(user);
        } else {
            if (userService.saveUser(user) == 0) {
                model.addAttribute("msg", "Username is already taken!");
                return "/register";
            } else {
                model.addAttribute("msg", "Successfully registered");
                return "/login";
            }
        }
        return "/login";
    }


    @RequestMapping(path = "/users/delete/{id}", method = RequestMethod.GET)
    public RedirectView deleteUserById(Model model, @PathVariable int id) {
        String res = userService.deleteUserById(id);
        model.addAttribute("message", res);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/users");
        return redirectView;

    }


    @RequestMapping(path = "users/info/{id}")
    public String showUserInfo(Model model, @PathVariable int id) {
        User userById = userService.getUserById(id);
        List<CourseDto> courseDtoList = courseService.getCoursesOfAuthor(id);
        model.addAttribute("courses", courseDtoList);
        model.addAttribute("user", userById);
        return "user-info";
    }
}
