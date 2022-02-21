package uz.pdp.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import uz.pdp.dto.AuthorDto;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.ModuleDto;
import uz.pdp.model.Course;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


@Component
@Repository
public class CourseDao {
    @Autowired
    SessionFactory sessionFactory;
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
            courseDto1.setImageUrl(rs.getString("image_url"));
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
        String sqlQuery = "insert into courses (name, description,image_url) values ('" + name + "', '" + description + "' ,'" + courseDto.getImageUrl() + "')";
        int res = template.update(sqlQuery);
        int courseId = template.queryForObject("select max(id) from courses ", (rs, row) -> {
            int result = rs.getInt(1);
            return result;
        });
        for (int authorsId : courseDto.getAuthorsIds()) {
            template.update("Insert into courses_users " +
                    " (author_id,course_id) values (" + authorsId + "," + courseId + ");");
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
            courseDto1.setImageUrl(rs.getString(5));
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
        template.update(" delete  from courses_users where course_id =" + id);
        return template.update("delete from courses where id = " + id);
    }

    public void editCourse(CourseDto courseDto) {
        String queryStr = "update courses " +
                "set name = '" + courseDto.getName() +
                "',updated_at=now() " +
                ",description = '" + courseDto.getDescription() +
                "',image_url = '" + courseDto.getImageUrl() +
                "' where id = " + courseDto.getId();
        template.update(queryStr);

        template.update("delete from courses_users where course_id=" + courseDto.getId());

        for (int authorsId : courseDto.getAuthorsIds()) {
            template.update("insert into courses_users " +
                    " (author_id,course_id) values (" + authorsId + "," + courseDto.getId() + ");");

        }
    }

    public List<CourseDto> getCoursesOfAuthor(int id) {
        String queryStr = "select c.id, c.name, c.is_active, c.description from courses c " +
                " join courses_users uc on uc.course_id = c.id " +
                " where uc.author_id =" + id;
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

