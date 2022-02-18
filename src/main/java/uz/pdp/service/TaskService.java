package uz.pdp.service;
//Sevinch Abdisattorova 02/18/2022 1:35 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.TaskDao;

@Service
public class TaskService {
    @Autowired
    TaskDao taskDao;
}
