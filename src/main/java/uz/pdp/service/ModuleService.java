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

    public String addModule(Module module) {
        if (moduleDao.addModule(module) == 1) {
            return "Successfully added!";
        }
        return "Not added!";
    }


    public String editModule(Module module) {
        int res = moduleDao.editModule(module);
        if (res == 1) {
            return "Successfully edited!";
        }
        return "Not edited";
    }

    public int deleteModuleById(int id) {
        int i = moduleDao.deleteModuleByIdFromDb(id);
        return i;
    }

    public Module getModuleById(int id) {
        return moduleDao.getModuleById(id);
    }
}
