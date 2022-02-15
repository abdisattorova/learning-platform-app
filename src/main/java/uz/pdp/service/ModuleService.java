package uz.pdp.service;
//Sevinch Abdisattorova 02/15/2022 9:15 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.ModuleDao;
import uz.pdp.model.Module;

@Service
public class ModuleService {

    @Autowired
    ModuleDao moduleDao;

    public int addModule(Module module) {
        return  moduleDao.addModule(module);
    }

    public void editModule(Module module) {
        moduleDao.editModule(module);
    }
}
