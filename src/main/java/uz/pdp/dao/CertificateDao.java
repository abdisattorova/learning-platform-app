package uz.pdp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Certificate;

@Repository
public class CertificateDao {
    @Autowired
    JdbcTemplate template;

    @Autowired
    SessionFactory sessionFactory;


    public void saveCertificate(Certificate certificate) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(certificate);
    }

    public Integer getCertificateId(int courseId, int userId) {
        String sqlQuery = "select max(id) from certificates where course_id=1 and user_id=2";
        Integer certificateId = template.queryForObject(sqlQuery, (rs, rowNum) -> {
            return rs.getInt(1);
        });
        return certificateId;
    }
}
