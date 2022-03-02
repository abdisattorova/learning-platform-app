package uz.pdp.controller;
//Sevinch Abdisattorova 02/15/2022 9:23 PM


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import uz.pdp.dto.ModuleDto;
import uz.pdp.model.Course;
import uz.pdp.model.Module;
import uz.pdp.service.ModuleService;

@Controller
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    ModuleService moduleService;


    @GetMapping(path = "/delete")
    public RedirectView deleteModule(
            @RequestParam(name = "courseId") int courseId,
            @RequestParam(name = "id") int id,
            Model model) {
        RedirectView redirectView = new RedirectView();
        String msg = "Successfully deleted!";
        if (moduleService.deleteModuleById(id) == 0) {
            msg = "Not deleted!";
        }

        model.addAttribute("msg", msg);
        redirectView.setUrl("/courses/info/" + courseId);
        return redirectView;
    }

    @PostMapping
    public RedirectView editOrAdd(Module module,
                                  Model model,
                                  @RequestParam(name = "courseId") int courseId) {
        RedirectView redirectView = new RedirectView();
        module.setCourse(new Course(courseId));
        String msg = "";
        if (module.getId() != 0) {
            msg = moduleService.editModule(module);
        } else {
            msg = moduleService.addModule(module);
        }
        model.addAttribute("msg", msg);
        redirectView.setUrl("/courses/info/" + courseId);
        return redirectView;
    }


    @GetMapping(path = "/form")
    public String getModuleForm(
            @RequestParam(name = "courseId") int courseId,
            Model model,
            @RequestParam(name = "id", required = false,
                    defaultValue = "0") int id) {
        model.addAttribute("courseId", courseId);
        if (id == 0) return "jsp/module-form";

        ModuleDto module = moduleService.getModuleById(id);
        if (module != null) {
            model.addAttribute("module", module);
            return "jsp/module-form";

        } else {
            model.addAttribute("message", "Module not found!!");
            return "redirect:/courses/info/" + courseId;
        }
    }
}