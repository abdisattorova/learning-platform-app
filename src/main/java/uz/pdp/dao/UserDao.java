package uz.pdp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.model.User;
import uz.pdp.util.Constants;

import java.util.List;

@Component
public class UserDao {

    @Autowired
    JdbcTemplate template;

    public List<User> getUsers(int page) {
        String queryStr = "select id, full_name, username, password,image_url from users " +

                " limit " + Constants.number_of_elements_in_1_page + " offset " +
                (page - 1) * Constants.number_of_elements_in_1_page;
        List<User> list = template.query(queryStr, (rs, row) -> {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setFullName(rs.getString(2));
            user.setUsername(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setImageUrl(rs.getString(5));
            return user;
        });
        return list;
    }

    public int saveUserToDb(User user) {
        String fullName = user.getFullName();
        String username = user.getUsername();
        String password = user.getPassword();
        String imageUrl = user.getImageUrl();
        try {
            return template.update("insert into users" +
                    " (full_name, username, password,image_url)" +
                    " values ('" + fullName + "', '" +
                    username + "','" + password + "' ,'" + imageUrl + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    public User getUserByIdFromDb(int id) {
        User user = null;
        try {
            user = template.queryForObject("select * from users where id= ?", new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return user;
        }

    }

    public int deleteUserByIdFromDb(int id) {
        try {
            template.update(" delete from users_courses where user_id=" + id);
            template.update("delete from users where id = " + id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public void editUser(User user) {
        String queryStr = "update users " +
                "set full_name = '" + user.getFullName() +
                "',username ='" + user.getUsername() + "' " +
                ",password = '" + user.getPassword() +
                "',image_url ='" + user.getImageUrl() +
                "' where id = " + user.getId();
        template.update(queryStr);
    }

    public List<User> getAuthors() {
        String queryStr = "select id, full_name, username, password from users where role ='AUTHOR'";
        List<User> list = template.query(queryStr, (rs, row) -> {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setFullName(rs.getString(2));
            user.setUsername(rs.getString(3));
            user.setPassword(rs.getString(4));
            return user;
        });
        return list;
    }

    public User getUserByUsernamePassword(String username, String password) {
        User userRes = null;
        try {
            String queryStr = "select * from users where username='" + username + "' and password ='" + password + "';";
            userRes = template.queryForObject(queryStr, (rs, row) -> {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setFullName(rs.getString(2));
                user.setUsername(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setRole(rs.getString(5));
                return user;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRes;
    }

    public int countUsers() {
        String query = " Select count(*) from users";
        Integer integer = template.queryForObject(query, (rs, rowNum) -> {
            return rs.getInt(1);
        });
        return integer;
    }
}
