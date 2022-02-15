package uz.pdp.controller;
//Sevinch Abdisattorova 02/15/2022 9:23 PM


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.model.Module;
import uz.pdp.service.ModuleService;

@Controller
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    ModuleService moduleService;

    @GetMapping()
    public String editOrAdd(Module module) {
        if (module.getId() != 0) {
            int res = moduleService.addModule(module);
        }
        {
            moduleService.editModule(module);
        }
        return "";
    }

    public String editModule(Module module) {
        //logic

        return "";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteModule(@PathVariable int id) {
        //logic
        return "";
    }

    @GetMapping(path = "/form")
    public String getModule(Model model, @RequestParam(name = "id", required = false, defaultValue = "0") int id) {
        if (id == 0) return "module-form";
        // TODO: 02/15/2022 jsp file

        // TODO: 02/15/2022  get module by id =>service=>dao

        // TODO: 02/15/2022 tekshir
/*        if (courseDto != null) {
            model.addAttribute("selectedCourse", courseDto);
            return "course-form";
        } else {
            model.addAttribute("message", "Course not found!!");
            return "redirect:/courses";
        }*/
        return "redirect:/courses";
    }
}
