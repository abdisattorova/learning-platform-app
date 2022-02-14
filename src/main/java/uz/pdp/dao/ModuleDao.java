package uz.pdp.dao;
//Sevinch Abdisattorova 02/13/2022 9:40 PM

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.dto.AuthorDto;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.ModuleDto;
import uz.pdp.model.Lesson;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class ModuleDao {

    @Autowired
    JdbcTemplate template;

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
}
