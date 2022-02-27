package uz.pdp.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import uz.pdp.dto.AuthorDto;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.ModuleDto;
import uz.pdp.dto.RateDto;
import uz.pdp.model.Course;
import uz.pdp.model.Rate;
import uz.pdp.model.User;

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
            courseDto1.setImageUrl(rs.getString(5));
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

    public int getAllAuthors() {
        String query = "select count(*)\n" +
                "from users\n" +
                "where role='MENTOR';";
        Integer authors = template.queryForObject(query, (rs, rowNum) -> {
            return rs.getInt(1);
        });
        return authors;
    }

    public int getAllStudent() {
        String query = "select count(*)\n" +
                "from users\n" +
                "where role='USER';";
        Integer students = template.queryForObject(query, (rs, rowNum) -> {
            return rs.getInt(1);
        });
        return students;
    }

    public int getAllCourseCount() {
        String query = "select count(*)\n" +
                "from courses";
        Integer course_count = template.queryForObject(query, (rs, rowNum) -> {
            return rs.getInt(1);
        });
        return course_count;

    }

    public int getAllTask() {
        String query = "select count(*)\n" +
                "from tasks;";
        Integer course_count = template.queryForObject(query, (rs, rowNum) -> {
            return rs.getInt(1);
        });
        return course_count;
    }

    public List<CourseDto> getStatisticsCourses() {
/*        String query = "select *\n" +
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
        return list;*/
        String query = "select c.name, count(t.id)\n" +
                "from users_tasks\n" +
                "         join tasks t on t.id = users_tasks.task_id\n" +
                "         join lessons l on l.id = t.lesson_id\n" +
                "         join modules m on m.id = l.module_id\n" +
                "         join courses c on c.id = m.course_id\n" +
                "where users_tasks.is_completed = true\n" +
                "group by c.name;";
        List<CourseDto> list = template.query(query, (rs, row) -> {
            CourseDto courseDto = new CourseDto();
            courseDto.setName(rs.getString(1));
            courseDto.setCount(rs.getInt(2));
            return courseDto;

        });
        return list;

    }

    public int countAllCourses() {
        Session currentSession = sessionFactory.getCurrentSession();
        NativeQuery nativeQuery = currentSession.createNativeQuery("select count(*) from courses");
        return (int) nativeQuery.uniqueResult();


    }

    public Course getCourse(int courseId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Course course = currentSession.get(Course.class, courseId);
        return course;
    }

    public void rateCourse(Rate rate) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(rate);
    }

    public RateDto checkCourseRate(int id, int user_id) {
        RateDto rate = null;
        try {
            String query = "select *\n" +
                    "from rates\n" +
                    "where course_id=" + id + " and user_id=" + user_id + ";";
            rate = template.queryForObject(query, (rs, rowNum) -> {
                RateDto rate1 = new RateDto();
                rate1.setId(rs.getInt(1));
                rate1.setRate(rs.getInt(2));
                rate1.setCourse_id(rs.getInt(3));
                rate1.setUser_id(rs.getInt(4));


                return rate1;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rate;
    }
}

