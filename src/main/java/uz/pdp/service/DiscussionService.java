package uz.pdp.service;
//Sevinch Abdisattorova 02/18/2022 1:35 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.DiscussionDao;
import uz.pdp.model.Discussion;

import javax.transaction.Transactional;
import java.util.List;

import static uz.pdp.util.Constants.getUserWithImageUrl;

@Service
public class DiscussionService {
    @Autowired
    DiscussionDao discussionDao;

    @Transactional
    public List<Discussion> getDiscussionsOfLesson(int lessonId) {
        List<Discussion> discussionsOfLesson = discussionDao.getDiscussionsOfLesson(lessonId);
        for (Discussion discussion : discussionsOfLesson) {
            getUserWithImageUrl(discussion.getUser());
        }
        return discussionsOfLesson;
    }

    @Transactional
    public void addDiscussion(Discussion discussion) {
        discussionDao.addDiscussion(discussion);
    }

    @Transactional
    public int checkDiscussion(Discussion discussion){
       return discussionDao.checkDiscuss(discussion);
    }

    @Transactional
    public void blockedUser(int user_id){
        discussionDao.changeBlockUser(user_id);
    }
}
