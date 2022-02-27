package uz.pdp.service;
//Sevinch Abdisattorova 02/26/2022 11:58 PM

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import uz.pdp.dao.MessageDao;
import uz.pdp.dao.UserDao;
import uz.pdp.dto.UserDto;
import uz.pdp.model.Message;
import uz.pdp.model.User;
import uz.pdp.util.Constants;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static uz.pdp.util.Constants.getUserWithImageUrl;
import static uz.pdp.util.Constants.promoteMessage;

@Service
public class MessageService {

    @Autowired
    UserDao userDao;

    @Autowired
    MessageDao messageDao;


    @Transactional
    public void getMessagingPeopleOfUser(Integer receiverId, Model model) {
        List<User> messagingPeopleOfUser = messageDao.getMessagingPeopleOfUser(receiverId);
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : messagingPeopleOfUser) {
            Integer countUnreadMessagesOfChat = messageDao.countUnreadMessagesOfChat(receiverId, user.getId());
            getUserWithImageUrl(user);
            UserDto userDto = new UserDto(user.getId(),
                    user.getFullName(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getRole(),
                    user.getImageUrl(),
                    user.getIs_blocked(),
                    countUnreadMessagesOfChat
            );
            userDtoList.add(userDto);
        }
        model.addAttribute("people", userDtoList);
    }

    @Transactional
    public void getMessagesWithPerson(Integer personId, User user, Model model) {
        model.addAttribute("user", user);
        User userByIdFromDb = userDao.getUserByIdFromDb(personId);
        model.addAttribute("person", userByIdFromDb);
        List<Message> messagesWithPerson = messageDao.getMessagesWithPerson(personId, user.getId());
        model.addAttribute("messages", messagesWithPerson);
    }


    @Transactional
    public void contactWithAdmin(Model model, User user) {
        User adminFromDb = userDao.getAdminFromDb();
        model.addAttribute("user", user);
        model.addAttribute("person", adminFromDb);
        List<Message> messagesWithPerson = messageDao.getMessagesWithPerson(adminFromDb.getId(), user.getId());
        model.addAttribute("messages", messagesWithPerson);
    }

    @Transactional
    public void saveMessage(User user, Integer personId, Model model, Message message) {
        User userByIdFromDb = userDao.getUserByIdFromDb(personId);
        message.setSender(user);
        message.setReceiver(userByIdFromDb);
        message.setCreated_at(LocalDateTime.now());
        message.setIsRead(false);
        messageDao.saveMessage(message);
    }

}
