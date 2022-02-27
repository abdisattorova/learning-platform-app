package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.model.enums.Role;
import uz.pdp.model.User;
import uz.pdp.util.Constants;

import java.util.List;

@Component
public class UserDao {

    @Autowired
    JdbcTemplate template;

    @Autowired
    SessionFactory sessionFactory;

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
            user = template.queryForObject("Select * from users where id =" + id, (rs, rowNum) -> {
                User user1 = new User();
                user1.setId(rs.getInt(1));
                user1.setFullName(rs.getString(2));
                user1.setUsername(rs.getString(3));
                user1.setPassword(rs.getString(4));
                user1.setRole(Role.valueOf(rs.getString(5)));
                user1.setImageUrl(rs.getString(6));
                user1.setIs_blocked(rs.getBoolean(7));
                return user1;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getAdminFromDb() {
        User user = null;
        try {
            user = template.queryForObject("Select * from users where role ='ADMIN'", (rs, rowNum) -> {
                User user1 = new User();
                user1.setId(rs.getInt(1));
                user1.setFullName(rs.getString(2));
                user1.setUsername(rs.getString(3));
                user1.setPassword(rs.getString(4));
                user1.setImageUrl(rs.getString(6));
                return user1;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public int deleteUserByIdFromDb(int id) {
        try {
            try {
                template.update(" delete from courses_users where author_id=" + id);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        String queryStr = "select id, full_name, username, password,image_url from users where role ='MENTOR'";
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
                user.setRole(Role.valueOf(rs.getString(5)));
                user.setImageUrl(rs.getString(6));
                user.setIs_blocked(rs.getBoolean(7));
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

    public List<User> getStudents() {
        String queryStr = "select id, full_name, username, password,image_url from users where role ='USER'";
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

    public void saveUser(User userById) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(userById);
    }


}
