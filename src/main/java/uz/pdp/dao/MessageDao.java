package uz.pdp.dao;
//Sevinch Abdisattorova 02/26/2022 11:58 PM


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Message;
import uz.pdp.model.User;
import uz.pdp.model.enums.Role;

import java.util.List;

@Repository
public class MessageDao {

    @Autowired
    SessionFactory sessionFactory;


    @Autowired
    JdbcTemplate template;

    public void saveMessage(Message message) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(message);
    }

    public List<User> getMessagingPeopleOfUser(Integer receiverId) {
        Session currentSession = sessionFactory.getCurrentSession();
        NativeQuery nativeQuery = currentSession.createSQLQuery(" select * from showAllMessagingPeople(" + receiverId + ")");
        nativeQuery.addEntity(User.class);
        List<User> messagingPeople = nativeQuery.list();
        return messagingPeople;

    }

    public Integer countUnreadMessagesOfChat(Integer receiverId, Integer id) {
        Integer integer = template.queryForObject("select count (*) from messages " +
                " where receiver_id=" + receiverId + " and sender_id=" + id + " and isread = 'false'", (rs, rowNum) -> {
            return rs.getInt(1);
        });
        return integer;
    }

    public List<Message> getMessagesWithPerson(Integer personId, Integer userId) {
        Session currentSession = sessionFactory.getCurrentSession();
        NativeQuery nativeQuery = currentSession.createNativeQuery("select * from " +
                "showallmessageswithperson(" + personId + "," + userId + ")");
        nativeQuery.addEntity(Message.class);
        return (List<Message>) nativeQuery.list();
    }

    public List<User> searchUsers(String search) {
        String queryStr = "select id, full_name, username, password,image_url,role" +
                " from users " +
                "where username like '%" + search + "%'" +
                "or full_name like '%" + search + "%'";
        List<User> list = template.query(queryStr, (rs, row) -> {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setFullName(rs.getString(2));
            user.setUsername(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setImageUrl(rs.getString(5));
            user.setRole(Role.valueOf(rs.getString(6)));
            return user;
        });
        return list;
    }
}
