package uz.pdp.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.dto.AuthorDto;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.ModuleDto;
import uz.pdp.model.User;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class CourseDao {

    @Autowired
    JdbcTemplate template;

    @Autowired
    ModuleDao moduleDao;

    public List<CourseDto> getCourses() {
        String query = "select *\n" +
                "from courses_infos()";
        List<CourseDto> list = template.query(query, (rs, row) -> {
            CourseDto courseDto1 = new CourseDto();
            courseDto1.setId(rs.getInt(1));
            courseDto1.setName(rs.getString(2));
            courseDto1.setDescription(rs.getString(3));
            courseDto1.setActive(rs.getBoolean(4));
            Array authors = rs.getArray("authors");
            Type type = new TypeToken<ArrayList<AuthorDto>>() {
            }.getType();
            List<AuthorDto> authorList = new Gson().fromJson(authors.toString(), type);
            courseDto1.setAuthorDtoList(authorList);
            List<ModuleDto> modulesOfCourses = moduleDao.getModulesOfCourses(courseDto1.getId());
            courseDto1.setModuleDtoList(modulesOfCourses);
            return courseDto1;
        });
        return list;
    }

    public int saveCourseToDb(CourseDto courseDto) {
        String name = courseDto.getName();
        String description = courseDto.getDescription();
        String sqlQuery = "insert into courses (name, description) values ('" + name + "', '" + description + "' )";
        int res = template.update(sqlQuery);
        int courseId = template.queryForObject("select max(id) from courses ", (rs, row) -> {
            int result = rs.getInt(1);
            return result;
        });

        for (int authorsId : courseDto.getAuthorsIds()) {
            template.update("Insert into users_courses " +
                    " (user_id,course_id) values (" + authorsId + "," + courseId + ");");

        }

        return res;


    }

    public CourseDto getCourseByIdFromDb(int id) {
        String query = "select *\n" +
                "from courses_info(" + id + ")";
        return template.queryForObject(query, (rs, rowNum) -> {
            CourseDto courseDto1 = new CourseDto();
            courseDto1.setId(rs.getInt(1));
            courseDto1.setName(rs.getString(2));
            courseDto1.setDescription(rs.getString(3));
            courseDto1.setActive(rs.getBoolean(4));
            Array authors = rs.getArray("authors");
            Type type = new TypeToken<ArrayList<AuthorDto>>() {
            }.getType();
            List<AuthorDto> authorList = new Gson().fromJson(authors.toString(), type);
            courseDto1.setAuthorDtoList(authorList);
            List<ModuleDto> modulesOfCourses = moduleDao.getModulesOfCourses(id);
            courseDto1.setModuleDtoList(modulesOfCourses);
            return courseDto1;
        });
    }

    public int deleteCourseByIdFromDb(int id) {
        template.update(" delete  from users_courses where course_id =" + id);
        return template.update("delete from courses where id = " + id);
    }

    public void editCourse(CourseDto courseDto) {
        String queryStr = "update courses " +
                "set name = '" + courseDto.getName() +
                "',updated_at=now() " +
                ",description = '" + courseDto.getDescription() +
                "' where id = " + courseDto.getId();
        template.update(queryStr);

    }

    public List<CourseDto> getCoursesOfAuthor(int id) {
        String queryStr = "select c.id, c.name, c.is_active, c.description from courses c " +
                " join users_courses uc on uc.course_id = c.id " +
                " where uc.user_id =" + id;
        List<CourseDto> list = template.query(queryStr, (rs, row) -> {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(rs.getInt(1));
            courseDto.setName(rs.getString(2));
            courseDto.setActive(rs.getBoolean(3));
            courseDto.setDescription(rs.getString(4));
            return courseDto;
        });
        return list;
    }

}
