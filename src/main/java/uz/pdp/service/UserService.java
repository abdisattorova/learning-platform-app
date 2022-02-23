package uz.pdp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.UserDao;
import uz.pdp.model.User;

import java.util.List;

@Service
public class UserService {


    @Autowired
    UserDao userDao;

    public List<User> getAllUsers(int page ) {

        return userDao.getUsers(page);

    }

    public int saveUser(User user) {
        return  userDao.saveUserToDb(user);
    }

    public User getUserById(int id) {
        if (id != 0) {
            User userById = userDao.getUserByIdFromDb(id);
            if (userById != null) return userById;
        }
        return null;
    }

    public String deleteUserById(int id) {
        int i = userDao.deleteUserByIdFromDb(id);
        if (i != 0) {
            return "Successfully deleted!!";
        } else return "Could not delete!!";
    }

    public void editUser(User user) {
        userDao.editUser(user);

    }

    public List<User> getAllAuthors() {
        return userDao.getAuthors();
    }
    public User getUserByUsernamePassword(String username, String password) {
        return userDao.getUserByUsernamePassword(username, password);

    }

    public int countAllUsers() {
        return userDao.countUsers();
    }

    public int countAllStudents(){
        return userDao.countStudents();

    }
    public int countAllMentors(){
        return userDao.countMentors();

    }
}
