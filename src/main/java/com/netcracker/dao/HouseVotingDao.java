package com.netcracker.dao;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.HouseVoting;

import java.math.BigInteger;

public interface HouseVotingDao {
    String GET_HOUSE_VOTING_BY_ANNOUNCEMENT_ID =
            "SELECT HVOTING.OBJECT_ID house_voting_id, \n" +
            "HVOTING_TITLE.VALUE      title, \n" +
            "ANNC.OBJECT_ID           announcement_id \n" +
            "FROM OBJECTS ANNC, \n" +
            "     OBJECTS HVOTING, \n" +
            "     ATTRIBUTES HVOTING_TITLE \n" +
            "WHERE ANNC.OBJECT_ID = ? \n" +
            "   AND HVOTING.PARENT_ID = ANNC.OBJECT_ID \n" +
            "   AND HVOTING_TITLE.ATTR_ID = 13 \n" +
            "   AND HVOTING_TITLE.OBJECT_ID = HVOTING.OBJECT_ID";

    String CREATE_HOUSE_VOTING_OBJECT =
            "MERGE INTO OBJECTS old\n" +
            "USING (SELECT seq_obj_next OBJECT_ID, ? PARENT_ID, 5 OBJECT_TYPE_ID,\n" +
            "'HouseVoting_' || seq_obj_curr NAME, NULL DESCRIPTION FROM DUAL) new\n" +
            "ON (old.OBJECT_ID = new.OBJECT_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "   UPDATE SET old.PARENT_ID = new.PARENT_ID, old.OBJECT_TYPE_ID = new.OBJECT_TYPE_ID,\n" +
            "       old.NAME = new.NAME, old.DESCRIPTION = new.DESCRIPTION\n" +
            "   WHERE old.PARENT_ID <> new.PARENT_ID OR old.OBJECT_TYPE_ID <> new.OBJECT_TYPE_ID OR\n" +
            "       old.NAME <> new.NAME OR old.DESCRIPTION <> new.DESCRIPTION\n" +
            "WHEN NOT MATCHED THEN\n" +
            "   INSERT(old.OBJECT_ID, old.PARENT_ID, old.OBJECT_TYPE_ID, old.NAME, old.DESCRIPTION)\n" +
            "   VALUES(seq_obj_curr, new.PARENT_ID, new.OBJECT_TYPE_ID, new.NAME, new.DESCRIPTION)";

    String CREATE_HOUSE_VOTING_ATTRIBUTES =
            "MERGE INTO ATTRIBUTES old\n" +
            "USING (SELECT 13 ATTR_ID, seq_obj_curr OBJECT_ID, ? VALUE FROM DUAL) new\n" +
            "ON (old.OBJECT_ID = new.OBJECT_ID AND old.ATTR_ID = new.ATTR_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "   UPDATE SET old.VALUE = new.VALUE\n" +
            "   WHERE old.VALUE <> new.VALUE\n" +
            "WHEN NOT MATCHED THEN\n" +
            "   INSERT(old.ATTR_ID, old.OBJECT_ID, old.VALUE)\n" +
            "   VALUES(new.ATTR_ID, new.OBJECT_ID, new.VALUE)\n";

    String DELETE_HOUSE_VOTING = "DELETE FROM OBJECTS WHERE OBJECT_TYPE_ID = 5 AND PARENT_ID = ?";

    String EXCEPTION_GET_HOUSE_VOTING_BY_ANNOUNCEMENT_ID = "Can't get house voting with this announcement id: ";
    String EXCEPTION_CREATE_HOUSE_VOTING = "Can't create announcement";
    String EXCEPTION_DELETE_HOUSE_VOTING = "Can't delete announcement with id: ";

    HouseVoting getHouseVotingByAnnouncementId(BigInteger announcementId) throws DaoAccessException;

    void createHouseVoting(HouseVoting houseVoting) throws DaoAccessException;

    void deleteHouseVoting(BigInteger announcementId) throws DaoAccessException;
}
