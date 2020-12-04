package com.netcracker.dao.impl;

import com.netcracker.dao.AnnouncementDao;
import com.netcracker.dao.mapper.AnnouncementMapper;
import com.netcracker.models.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public class AnnouncementDaoImpl implements AnnouncementDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String selectAllAnnouncements = "SELECT ANNC.OBJECT_ID announcement_id," +
                                    "ANNC_TITLE.VALUE title," +
                                    "ANNC_BODY.VALUE body," +
                                    "ANNC_IS_OPENED.VALUE is_opened," +
                                    "ANNC_CREATED_AT.VALUE created_at," +
                                    "ANNC_IS_OPENED.VALUE is_opened," +
                                    "HVOTING.OBJECT_ID house_voting_id" +
                                    "HVOTING_TITLE.VALUE title" +
                                    "FROM OBJECTS ANNC, OBJECTS HVOTING," +
                                    "ATTRIBUTES ANNC_TITLE, ATTRIBUTES ANNC_BODY," +
                                    "ATTRIBUTES ANNC_IS_OPENED, ATTRIBUTES ANNC_CREATED_AT," +
                                    "ATTRIBUTES HVOTING_TITLE" +
                                    "WHERE ANNC.OBJECT_TYPE_ID = 3" +
                                    "AND HVOTING.PARENT_ID = ANNC.OBJECT_ID" +
                                    "AND ANNC_TITLE.ATTR_ID = 7" +
                                    "AND ANNC_TITLE.OBJECT_ID = ANNC.OBJECT_ID" +
                                    "AND ANNC_BODY.ATTR_ID = 8" +
                                    "AND ANNC_BODY.OBJECT_ID = ANNC.OBJECT_ID" +
                                    "AND ANNC_IS_OPENED.ATTR_ID = 9" +
                                    "AND ANNC_IS_OPENED.OBJECT_ID  = ANNC.OBJECT_ID" +
                                    "AND ANNC_CREATED_AT.ATTR_ID = 10" +
                                    "AND ANNC_CREATED_AT.OBJECT_ID = ANNC.OBJECT_ID" +
                                    "AND HVOTING_TITLE.ATTR_ID = 13" +
                                    "AND HVOTING_TITLE.OBJECT_ID = HVOTING.OBJECT_ID";

    private static final String getAnnouncementById = "SELECT ANNC.OBJECT_ID announcement_id," +
                                    "ANNC_TITLE.VALUE title," +
                                    "ANNC_BODY.VALUE body," +
                                    "ANNC_IS_OPENED.VALUE is_opened," +
                                    "ANNC_CREATED_AT.VALUE created_at," +
                                    "HVOTING.OBJECT_ID house_voting_id," +
                                    "HVOTING_TITLE.VALUE title" +
                                    "FROM OBJECTS ANNC, OBJECTS HVOTING," +
                                    "ATTRIBUTES ANNC_TITLE, ATTRIBUTES ANNC_BODY," +
                                    "ATTRIBUTES ANNC_IS_OPENED, ATTRIBUTES ANNC_CREATED_AT," +
                                    "ATTRIBUTES HVOTING_TITLE" +
                                    "WHERE ANNC.OBJECT_ID = ?" +
                                    "AND HVOTING.PARENT_ID = ANNC.OBJECT_ID" +
                                    "AND ANNC_TITLE.ATTR_ID = 7" +
                                    "AND ANNC_TITLE.OBJECT_ID = ANNC.OBJECT_ID" +
                                    "AND ANNC_BODY.ATTR_ID = 8" +
                                    "AND ANNC_BODY.OBJECT_ID = ANNC.OBJECT_ID" +
                                    "AND ANNC_IS_OPENED.ATTR_ID = 9" +
                                    "AND ANNC_IS_OPENED.OBJECT_ID  = ANNC.OBJECT_ID" +
                                    "AND ANNC_CREATED_AT.ATTR_ID = 10" +
                                    "AND ANNC_CREATED_AT.OBJECT_ID = ANNC.OBJECT_ID" +
                                    "AND HVOTING_TITLE.ATTR_ID = 13" +
                                    "AND HVOTING_TITLE.OBJECT_ID = HVOTING.OBJECT_ID";

    private static final String createAnnouncement = "INSERT ALL " +
                                    "INSERT INTO (OBJECT_ID,PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION) " +
                                    "VALUES (obj_id_seq.nextval,NULL,3,'Announcement_' || obj_id_seq.currval,NULL)" +
                                    "INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                                    "VALUES(7,obj_id_seq.currval, ? )" +
                                    "INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                                    "VALUES(8,obj_id_seq.currval, ? )" +
                                    "INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                                    "VALUES(9,obj_id_seq.currval, ? )" +
                                    "INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                                    "VALUES(10,obj_id_seq.currval, ? )" +
                                    "SELECT * FROM DUAL";

    private static final String updateAnnouncement = "MERGE INTO ATTRIBUTES x" +
                                    "USING (SELECT 7 ATTR_ID, ? VALUE FROM DUAL" +
                                    "UNION ALL" +
                                    "SELECT 8, ? FROM DUAL " +
                                    "UNION ALL" +
                                    "SELECT 9, ? FROM DUAL " +
                                    "UNION ALL" +
                                    "SELECT 10, ? FROM DUAL) y " +
                                    "ON (x.OBJECT_ID = ?)" +
                                    "WHEN MATCHED THEN" +
                                    "    UPDATE SET x.ATTR_ID = y.ATTR_ID," +
                                    "               x.VALUE = y.VALUE," +
                                    "WHERE x.ATTR_ID <> y.ATTR_ID OR" +
                                    "           x.OBJECT_ID <> y.OBJECT_ID OR" +
                                    "           x.VALUE <> y.VALUE" +
                                    "WHEN NOT MATCHED THEN" +
                                    "INSERT(x.ATTR_ID,x.VALUE)" +
                                    "VALUES(y.ATTR_ID,y.VALUE)";

    private static final String deleteAnnouncement = "DELETE FROM OBJECTS WHERE OBJECT_ID = ?";

    @Override
    public List<Announcement> getAllAnnouncements() {
        return jdbcTemplate.query(selectAllAnnouncements, new AnnouncementMapper());
    }

    @Override
    public Announcement getAnnouncementById(BigInteger id) {
        Announcement announcement = jdbcTemplate.queryForObject(getAnnouncementById, new Object[] {id}, new AnnouncementMapper());

        return announcement;
    }

    @Override
    public void createAnnouncement(Announcement announcement) {

        jdbcTemplate.update(createAnnouncement,
                announcement.getTitle(),
                announcement.getBody(),
                announcement.getIsOpened(),
                announcement.getCreatedAt());
    }

    @Override
    public void updateAnnouncement(Announcement announcement) {
        jdbcTemplate.update(updateAnnouncement,
                announcement.getTitle(),
                announcement.getBody(),
                announcement.getIsOpened(),
                announcement.getCreatedAt(),
                announcement.getAnnouncementId());
    }

    @Override
    public void deleteAnnouncement(BigInteger id) {
        jdbcTemplate.update(deleteAnnouncement, id);
    }
}
