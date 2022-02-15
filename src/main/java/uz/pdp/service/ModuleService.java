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

    public int deleteModuleById(int id) {
        return moduleDao.deleteModuleByIdFromDb(id);
    }

<<<<<<< HEAD

    public String editModule(Module module) {
        int res = moduleDao.editModule(module);
        if (res == 1) {
            return "Successfully edited!";
        }
        return "Not edited";
=======
    public String deleteModuleById(int id) {
        int i = moduleDao.deleteModuleByIdFromDb(id);
        if (i != 0) {
            return "Successfully deleted!!";
        } else return "Could not delete!!";
>>>>>>> be8174d0c318c8afe88382bf2056d44ed4e75194
    }

    public Module getModuleById(int id) {
        return moduleDao.getModuleById(id);
    }
<<<<<<< HEAD
=======

    public Module getModuleById(int id) {
       return moduleDao.getModuleById(id);
    }
>>>>>>> be8174d0c318c8afe88382bf2056d44ed4e75194
}
