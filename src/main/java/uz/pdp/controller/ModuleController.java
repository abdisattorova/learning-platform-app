package uz.pdp.controller;
//Sevinch Abdisattorova 02/15/2022 9:23 PM


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import uz.pdp.model.Module;
import uz.pdp.service.ModuleService;

@Controller
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    ModuleService moduleService;

    @PostMapping
    public RedirectView editOrAdd(Module module, Model model) {
        String msg = "";
        if (module.getId() != 0) {
            msg = moduleService.editModule(module);
        } else {
            msg = moduleService.addModule(module);
        }
        model.addAttribute("msg", msg);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/courses/info/" + module.getCourseId());
        return redirectView;
    }


    @GetMapping(path = "/delete")
    public RedirectView deleteModule(
            @RequestParam(name = "courseId") int courseId,
            @RequestParam(name = "id") int id,
            Model model) {
        RedirectView redirectView = new RedirectView();
        String msg = "Successfully deleted!";
        if (moduleService.deleteModuleById(id) == 0) {
            msg = "Not deleted!";
            public String editOrAdd (Module module){
                if (module.getId() != 0) {
                    moduleService.editModule(module);
                } else {
                    int res = moduleService.addModule(module);
                }
                model.addAttribute("msg", msg);
                redirectView.setUrl("/courses/info/" + courseId);
                return redirectView;
            }

            public String editModule (Module module){
                //logic

                return "";
            }

            @GetMapping(path = "/delete/{id}")
            public String deleteModule ( @PathVariable int id, Model model){
                //logic
                String res = moduleService.deleteModuleById(id);

                model.addAttribute("result", res);

                return "";
            }

            @GetMapping(path = "/form")
            public String getModule (
            @RequestParam(name = "courseId") int courseId,
            Model model,
            @RequestParam(name = "id", required = false,
            defaultValue = "0")int id){
                model.addAttribute("courseId", courseId);
                if (id == 0) return "module-form";


                // TODO: 02/15/2022  get module by id =>service=>dao

                // TODO: 02/15/2022 tekshir
                Module module = moduleService.getModuleById(id);
                if (module != null) {
                    model.addAttribute("module", module);
                    return "module-form";

                } else {
                    model.addAttribute("message", "Module not found!!");
                    return "redirect:/courses/info/" + courseId;
                }

            }
//        else {
//            model.addAttribute("message", "Module not found!!");
//            return "redirect:/courses";
//        }
            return "";
//        moduleService.getModuleById(id);
/*        if (courseDto != null) {
            model.addAttribute("selectedCourse", courseDto);
            return "course-form";
        } else {
            model.addAttribute("message", "Course not found!!");
            return "redirect:/courses";
        }*/
//        return "redirect:/courses";
        }
    }
}
