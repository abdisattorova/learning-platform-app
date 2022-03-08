package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import uz.pdp.dto.CourseDto;
import uz.pdp.model.User;
import uz.pdp.service.CourseService;
import uz.pdp.service.UserService;
import uz.pdp.util.Constants;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static uz.pdp.util.Constants.getUserWithImageUrl;
import static uz.pdp.util.Constants.path;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseController controller;


    @RequestMapping(path = "/login")
    public String showLoginForm() {
        return "jsp/login";
    }


    @RequestMapping(path = "/register")
    public String showRegisterForm(@ModelAttribute("user") User user) {
        System.out.println(55);
        return "thymeleaf/register";

    }

    @RequestMapping(path = "/faq")
    public String showFaq(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "jsp/faq";
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public String getAllUsers(Model model, @RequestParam(defaultValue = "1") Integer page) {
        List<User> allUsers = userService.getAllUsers(page);
        for (User allUser : allUsers) {
            getUserWithImageUrl(allUser);
        }
        int count = userService.countAllUsers();
        int pages = count / Constants.number_of_elements_in_1_page; //count 10  10/3=3
        int remainder = count % Constants.number_of_elements_in_1_page; //10    10%3=1

        if (remainder > 0) {
            pages = pages + 1;
        }

        model.addAttribute("pages", pages);
        model.addAttribute("userList", allUsers);
        return "jsp/view-users";

    }


    @RequestMapping(path = "/students", method = RequestMethod.GET)
    public String getAllStudents(Model model) {
        List<User> allUsers = userService.getAllStudents();
        for (User allUser : allUsers) {
            getUserWithImageUrl(allUser);
        }
        model.addAttribute("userList", allUsers);
        return "jsp/view-users";

    }

    @RequestMapping(path = "/mentors", method = RequestMethod.GET)
    public String getAllMentors(Model model) {
        List<User> allUsers = userService.getAllAuthors();
        for (User allUser : allUsers) {
            getUserWithImageUrl(allUser);
        }
        model.addAttribute("userList", allUsers);
        return "jsp/view-users";

    }


    @RequestMapping(path = "/users/login", method = RequestMethod.POST)
    public String authUser(User user, Model model, HttpSession session) {

        String password = user.getPassword();
        String username = user.getUsername();
        User userFromDb = userService.getUserByUsernamePassword(username, password);
        if (userFromDb != null) {
            getUserWithImageUrl(userFromDb);
            session.setAttribute("user", userFromDb);
            model.addAttribute("msg", "Welcome " + userFromDb.getFullName());
            if (!userFromDb.getIs_blocked()) {
                return "redirect:/courses";
            } else return "jsp/block-page";
        }
        model.addAttribute("msg", "Username or password is incorrect!");
        return "jsp/login";

    }


    @RequestMapping(path = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:login";
    }


    @RequestMapping("/users/form")
    public String getUserForm(Model model, @RequestParam(name = "id", required = false, defaultValue = "0") int id) {
        if (id == 0) return "jsp/user-form";
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("selectedUser", user);
            return "jsp/user-form";
        } else {
            model.addAttribute("message",
                    "User not found!!");
            return "redirect:/users";
        }
    }


    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") @Valid User user,
                          BindingResult result,
                          @RequestParam(value = "file", required = false) CommonsMultipartFile file,
                          Model model) {
        if (userService.saveUser(user) == 0) {
            FieldError fieldError = new FieldError("username", "username", "Username is already taken!");
            result.addError(fieldError);
        }
        if (result.hasErrors()) {
            return "thymeleaf/register";
        }
        String filename = "";
        if (file != null) {
            if (file.getOriginalFilename().endsWith(".jpg")
                    || file.getOriginalFilename().endsWith(".png")) {
                filename = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = null;
                try {
                    String imgPath = path + filename;
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(imgPath)));
                    stream.write(bytes);
                    stream.flush();
                    stream.close();
                    user.setImageUrl(filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (file == null && user.getImageUrl() != null) {
        } else {
            filename = "profile.jpg";
            user.setImageUrl(filename);
        }
        if (user.getId() != null) {
            userService.editUser(user);
        } else {
            if (userService.saveUser(user) == 0) {
                model.addAttribute("msg", "Username is already taken!");
                return "jsp/register";
            } else {
                model.addAttribute("msg", "Successfully registered");
                return "jsp/login";
            }
        }
        return "jsp/login";
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
    public String showUserInfo(Model model, @PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        User userById = userService.getUserById(id);
        getUserWithImageUrl(userById);
        List<CourseDto> courseDtoList = courseService.getCoursesOfAuthor(id);
        model.addAttribute("courses", courseDtoList);
        model.addAttribute("user", userById);
        if (user.getRole().name().equals("ADMIN")) {
            model.addAttribute("admin", user);
        }
        return "jsp/user-info";
    }


    @GetMapping(path = "/users/block/{userId}")
    public String blockUser(@PathVariable(name = "userId") Integer userId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        User userById = userService.getUserById(userId);
        userService.blockUser(userById);
        model.addAttribute("user", userById);
        if (user.getRole().name().equals("ADMIN")) {
            model.addAttribute("admin", user);
        }
        return "jsp/user-info";
    }

    @GetMapping(path = "/users/role/{userId}")
    public String changeRole(@PathVariable Integer userId, Model model, HttpSession session) {
        userService.changeRoleOfUser(userId);
        User user = (User) session.getAttribute("user");
        User userById = userService.getUserById(userId);
        if (user.getRole().name().equals("ADMIN")) {
            model.addAttribute("admin", user);
        }
        model.addAttribute("user", userById);
        return "jsp/user-info";

    }


    @GetMapping("/about")
    public String aboutUs(Model model) {
        List<User> users = userService.getAbout();
        for (User allUser : users) {
            getUserWithImageUrl(allUser);
        }
        model.addAttribute("members", users);
        return "jsp/team";
    }

}
