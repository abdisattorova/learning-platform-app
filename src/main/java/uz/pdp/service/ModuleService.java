package uz.pdp.service;
//Sevinch Abdisattorova 02/15/2022 9:15 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.ModuleDao;

@Service
public class ModuleService {

    @Autowired
    ModuleDao moduleDao;

}
