package uz.pdp.dao;
//Sevinch Abdisattorova 02/13/2022 9:40 PM

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.dto.ModuleDto;
import uz.pdp.model.Lesson;
import uz.pdp.model.Module;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class ModuleDao {

    @Autowired
    JdbcTemplate template;

    @Autowired
    SessionFactory sessionFactory;

    public List<ModuleDto> getModulesOfCourses(int courseId) {
        String query = "SELECT * FROM get_module_lessons(" + courseId + ")";

        List<ModuleDto> list = template.query(query, (rs, row) -> {
            ModuleDto moduleDto = new ModuleDto();
            moduleDto.setId(rs.getInt(1));
            moduleDto.setName(rs.getString(2));
            Array lessonsArray = rs.getArray("lessons");
            Type type = new TypeToken<ArrayList<Lesson>>() {
            }.getType();
            List<Lesson> lessonList = new Gson().fromJson(lessonsArray.toString(), type);
            moduleDto.setLessons(lessonList);
            return moduleDto;
        });
        return list;

    }


    public int deleteModuleByIdFromDb(int id) {
        try {
            String query = " delete  from modules where id =" + id + " returning course_id";
            Integer integer = template.queryForObject(query, (rs, rowNum) -> {
                return rs.getInt(1);
            });
            return integer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int addModule(Module module) {
        try {
            String query = "insert into modules (name, course_id) " +
                    "values ('" + module.getName() + "'," + module.getCourse().getId() + ");";
            int update = template.update(query);
            return update;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int editModule(Module module) {
        try {
            String query = "update modules set name = '"
                    + module.getName() + "'where id=" + module.getId();
            int update = template.update(query);
            return update;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public ModuleDto getModuleById(int id) {
        ModuleDto resModule = null;
        try {

            String query = "SELECT * FROM modules where id=" + id;
            resModule = template.queryForObject(query, (rs, rowNum) -> {
                ModuleDto module = new ModuleDto();
                module.setId(rs.getInt(1));
                module.setName(rs.getString(2));
                module.setCourseId(rs.getInt(3));
                return module;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resModule;
    }

    public Module getModule(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Module.class, id);
    }
}
