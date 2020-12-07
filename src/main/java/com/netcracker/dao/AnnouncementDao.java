package com.netcracker.dao;

import com.netcracker.models.Announcement;

import java.math.BigInteger;
import java.util.List;

public interface AnnouncementDao {
    String GET_ALL_ANNOUNCEMENTS =
            "SELECT ANNC.OBJECT_ID announcement_id,\n" +
            "ANNC_TITLE.VALUE title,\n" +
            "ANNC_BODY.VALUE body,\n" +
            "ANNC_IS_OPENED.VALUE is_opened,\n" +
            "ANNC_CREATED_AT.VALUE created_at,\n" +
            "HVOTING.OBJECT_ID house_voting_id,\n" +
            "HVOTING_TITLE.VALUE title\n" +
            "FROM ATTRIBUTES ANNC_TITLE, ATTRIBUTES ANNC_BODY,\n" +
            "     ATTRIBUTES ANNC_IS_OPENED, ATTRIBUTES ANNC_CREATED_AT, OBJECTS ANNC\n" +
            "LEFT JOIN OBJECTS HVOTING ON (HVOTING.PARENT_ID = ANNC.OBJECT_ID)\n" +
            "LEFT JOIN ATTRIBUTES HVOTING_TITLE ON (HVOTING_TITLE.OBJECT_ID = HVOTING.OBJECT_ID)\n" +
            "WHERE ANNC.OBJECT_TYPE_ID = 3\n" +
            "   AND ANNC_TITLE.ATTR_ID = 7\n" +
            "   AND ANNC_TITLE.OBJECT_ID = ANNC.OBJECT_ID\n" +
            "   AND ANNC_BODY.ATTR_ID = 8\n" +
            "   AND ANNC_BODY.OBJECT_ID = ANNC.OBJECT_ID\n" +
            "   AND ANNC_IS_OPENED.ATTR_ID = 9\n" +
            "   AND ANNC_IS_OPENED.OBJECT_ID  = ANNC.OBJECT_ID\n" +
            "   AND ANNC_CREATED_AT.ATTR_ID = 10\n" +
            "   AND ANNC_CREATED_AT.OBJECT_ID = ANNC.OBJECT_ID\n" +
            "   AND (HVOTING_TITLE.ATTR_ID = 13 OR HVOTING_TITLE.ATTR_ID IS NULL)";

    String GET_ANNOUNCEMENT_BY_ID =
            "SELECT ANNC.OBJECT_ID announcement_id,\n" +
            "ANNC_TITLE.VALUE title,\n" +
            "ANNC_BODY.VALUE body,\n" +
            "ANNC_IS_OPENED.VALUE is_opened,\n" +
            "ANNC_CREATED_AT.VALUE created_at,\n" +
            "HVOTING.OBJECT_ID house_voting_id,\n" +
            "HVOTING_TITLE.VALUE title\n" +
            "FROM ATTRIBUTES ANNC_TITLE, ATTRIBUTES ANNC_BODY,\n" +
            "     ATTRIBUTES ANNC_IS_OPENED, ATTRIBUTES ANNC_CREATED_AT, OBJECTS ANNC\n" +
            "LEFT JOIN OBJECTS HVOTING ON (HVOTING.PARENT_ID = ANNC.OBJECT_ID)\n" +
            "LEFT JOIN ATTRIBUTES HVOTING_TITLE ON (HVOTING_TITLE.OBJECT_ID = HVOTING.OBJECT_ID)\n" +
            "   WHERE ANNC.OBJECT_ID = ?\n" +
            "   AND ANNC_TITLE.ATTR_ID = 7\n" +
            "   AND ANNC_TITLE.OBJECT_ID = ANNC.OBJECT_ID\n" +
            "   AND ANNC_BODY.ATTR_ID = 8\n" +
            "   AND ANNC_BODY.OBJECT_ID = ANNC.OBJECT_ID\n" +
            "   AND ANNC_IS_OPENED.ATTR_ID = 9\n" +
            "   AND ANNC_IS_OPENED.OBJECT_ID  = ANNC.OBJECT_ID\n" +
            "   AND ANNC_CREATED_AT.ATTR_ID = 10\n" +
            "   AND ANNC_CREATED_AT.OBJECT_ID = ANNC.OBJECT_ID\n" +
            "   AND (HVOTING_TITLE.ATTR_ID = 13 OR HVOTING_TITLE.ATTR_ID IS NULL)";

    String CREATE_ANNOUNCEMENT_OBJECT =
            "MERGE INTO OBJECTS old\n" +
            "USING (SELECT seq_obj_next OBJECT_ID, NULL PARENT_ID, 3 OBJECT_TYPE_ID,\n" +
            "       'Announcement_' || seq_obj_curr NAME, NULL DESCRIPTION FROM DUAL) new\n" +
            "ON (old.OBJECT_ID = new.OBJECT_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "   UPDATE SET old.PARENT_ID = new.PARENT_ID, old.OBJECT_TYPE_ID = new.OBJECT_TYPE_ID,\n" +
            "       old.NAME = new.NAME, old.DESCRIPTION = new.DESCRIPTION\n" +
            "   WHERE old.PARENT_ID <> new.PARENT_ID OR old.OBJECT_TYPE_ID <> new.OBJECT_TYPE_ID OR\n" +
            "       old.NAME <> new.NAME OR old.DESCRIPTION <> new.DESCRIPTION\n" +
            "WHEN NOT MATCHED THEN \n" +
            "   INSERT(old.OBJECT_ID, old.PARENT_ID, old.OBJECT_TYPE_ID, old.NAME, old.DESCRIPTION) \n" +
            "   VALUES(seq_obj_curr, new.PARENT_ID, new.OBJECT_TYPE_ID, new.NAME, new.DESCRIPTION)";

    String CREATE_ANNOUNCEMENT_ATTRIBUTES =
            "MERGE INTO ATTRIBUTES old\n" +
            "USING (SELECT 7 ATTR_ID, seq_obj_curr OBJECT_ID, 'WELCOME' VALUE, NULL DATE_VALUE FROM DUAL\n" +
            "   UNION ALL\n" +
            "   SELECT 8, seq_obj_curr, 'Welcome to our system.', NULL FROM DUAL\n" +
            "   UNION ALL\n" +
            "   SELECT 9, seq_obj_curr, 'false', NULL FROM DUAL\n" +
            "   UNION ALL\n" +
            "   SELECT 10, seq_obj_curr, NULL, sysdate FROM DUAL) new\n" +
            "ON (old.OBJECT_ID = new.OBJECT_ID AND old.ATTR_ID = new.ATTR_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "   UPDATE SET old.VALUE = new.VALUE, old.DATE_VALUE = new.DATE_VALUE\n" +
            "   WHERE old.VALUE <> new.VALUE OR old.DATE_VALUE <> new.DATE_VALUE\n" +
            "WHEN NOT MATCHED THEN\n" +
            "   INSERT(old.ATTR_ID, old.OBJECT_ID, old.VALUE, old.DATE_VALUE)\n" +
            "   VALUES(new.ATTR_ID, new.OBJECT_ID, new.VALUE, new.DATE_VALUE)";

    String UPDATE_ANNOUNCEMENT =
            "MERGE INTO ATTRIBUTES old\n" +
            "USING (SELECT 7 ATTR_ID, ? VALUE, NULL DATE_VALUE FROM DUAL\n" +
            "   UNION ALL\n" +
            "   SELECT 8, ?, NULL FROM DUAL\n" +
            "   UNION ALL\n" +
            "   SELECT 9, ?, NULL FROM DUAL\n" +
            "   UNION ALL\n" +
            "   SELECT 10, NULL, ? FROM DUAL) new\n" +
            "ON (old.OBJECT_ID = ? AND old.ATTR_ID = new.ATTR_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "   UPDATE SET old.VALUE = new.VALUE, old.DATE_VALUE = new.DATE_VALUE\n" +
            "   WHERE old.VALUE <> new.VALUE OR old.DATE_VALUE <> new.DATE_VALUE";

    String DELETE_ANNOUNCEMENT = "DELETE FROM OBJECTS WHERE OBJECT_ID = ?";

    List<Announcement> getAllAnnouncements();

    Announcement getAnnouncementById(BigInteger id);

    void createAnnouncement(Announcement announcement);

    void updateAnnouncement(Announcement announcement);

    void deleteAnnouncement(BigInteger id);
}
