package com.netcracker.dao;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Apartment;
import com.netcracker.models.VotingOption;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public interface VotingOptionDao {
    String GET_ALL_VOTING_OPTIONS_BY_ANNOUNCEMENT_ID =
            "SELECT VOTING_OPTION.OBJECT_ID voting_option_id,\n" +
            "       VOTING_OPTION_NAME.VALUE       name,\n" +
            "       HVOTING.OBJECT_ID              house_voting_id,\n" +
            "       COUNT(VOTE.REFERENCE)          count\n" +
            "FROM OBJECTS HVOTING,\n" +
            "     OBJECTS ANNC,\n" +
            "     ATTRIBUTES VOTING_OPTION_NAME,\n" +
            "     OBJECTS VOTING_OPTION\n" +
            "LEFT JOIN  OBJREFERENCE VOTE ON (VOTE.ATTR_ID = 30 AND VOTE.OBJECT_ID = VOTING_OPTION.OBJECT_ID)\n" +
            "WHERE VOTING_OPTION.OBJECT_TYPE_ID = 6\n" +
            "    AND ANNC.OBJECT_ID = ?\n" +
            "    AND HVOTING.PARENT_ID = ANNC.OBJECT_ID\n" +
            "    AND VOTING_OPTION.PARENT_ID = HVOTING.OBJECT_ID\n" +
            "    AND VOTING_OPTION_NAME.ATTR_ID = 14\n" +
            "    AND VOTING_OPTION_NAME.OBJECT_ID = VOTING_OPTION.OBJECT_ID\n" +
            "GROUP BY VOTING_OPTION.OBJECT_ID, VOTING_OPTION_NAME.VALUE, HVOTING.OBJECT_ID";

    String CREATE_VOTING_OPTION_OBJECT =
            "MERGE INTO OBJECTS old\n" +
            "USING (SELECT seq_obj_next OBJECT_ID, ? PARENT_ID, 6 OBJECT_TYPE_ID,\n" +
            "'VotingOption_' || seq_obj_curr NAME, NULL DESCRIPTION FROM DUAL) new\n" +
            "ON (old.OBJECT_ID = new.OBJECT_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "   UPDATE SET old.PARENT_ID = new.PARENT_ID, old.OBJECT_TYPE_ID = new.OBJECT_TYPE_ID,\n" +
            "       old.NAME = new.NAME, old.DESCRIPTION = new.DESCRIPTION\n" +
            "   WHERE old.PARENT_ID <> new.PARENT_ID OR old.OBJECT_TYPE_ID <> new.OBJECT_TYPE_ID OR\n" +
            "       old.NAME <> new.NAME OR old.DESCRIPTION <> new.DESCRIPTION\n" +
            "WHEN NOT MATCHED THEN\n" +
            "   INSERT(old.OBJECT_ID, old.PARENT_ID, old.OBJECT_TYPE_ID, old.NAME, old.DESCRIPTION)\n" +
            "   VALUES(seq_obj_curr, new.PARENT_ID, new.OBJECT_TYPE_ID, new.NAME, new.DESCRIPTION)";

    String CREATE_VOTING_OPTION_ATTRIBUTES =
            "MERGE INTO ATTRIBUTES old\n" +
            "USING (SELECT 14 ATTR_ID, seq_obj_curr OBJECT_ID, ? VALUE FROM DUAL) new\n" +
            "ON (old.OBJECT_ID = new.OBJECT_ID AND old.ATTR_ID = new.ATTR_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "   UPDATE SET old.VALUE = new.VALUE\n" +
            "   WHERE old.VALUE <> new.VALUE\n" +
            "WHEN NOT MATCHED THEN\n" +
            "   INSERT(old.ATTR_ID, old.OBJECT_ID, old.VALUE)\n" +
            "   VALUES(new.ATTR_ID, new.OBJECT_ID, new.VALUE)";

    String ADD_VOTE =
            "MERGE INTO OBJREFERENCE old\n" +
            "USING (SELECT 30 ATTR_ID, ? OBJECT_ID, ? REFERENCE FROM DUAL) new\n" +
            "ON (old.ATTR_ID = new.ATTR_ID AND old.OBJECT_ID = new.OBJECT_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "   UPDATE SET old.REFERENCE = new.REFERENCE\n" +
            "   WHERE old.REFERENCE <> new.REFERENCE\n" +
            "WHEN NOT MATCHED THEN\n" +
            "   INSERT(old.ATTR_ID,old.OBJECT_ID, old.REFERENCE)\n" +
            "   VALUES(new.ATTR_ID,new.OBJECT_ID, new.REFERENCE)";

    String GET_ALL_APARTMENTS_BY_VOTING_OPTION_ID =
            "SELECT APRT.OBJECT_ID  account_id,\n" +
            "       APTNUM.VALUE    apartment_number,\n" +
            "       FNAME.VALUE     first_name,\n" +
            "       LNAME.VALUE     last_name\n" +
            "FROM OBJREFERENCE VOTE,\n" +
            "     OBJECTS APRT,\n" +
            "     ATTRIBUTES APTNUM,\n" +
            "     ATTRIBUTES FNAME,\n" +
            "     ATTRIBUTES LNAME\n" +
            "WHERE VOTE.ATTR_ID = 30\n" +
            "    AND VOTE.OBJECT_ID = ?\n" +
            "    AND VOTE.REFERENCE = APRT.OBJECT_ID\n" +
            "    AND APTNUM.OBJECT_ID = APRT.OBJECT_ID\n" +
            "    AND APTNUM.ATTR_ID = 15\n" +
            "    AND FNAME.OBJECT_ID = APRT.OBJECT_ID\n" +
            "    AND FNAME.ATTR_ID = 4\n" +
            "    AND LNAME.OBJECT_ID = APRT.OBJECT_ID\n" +
            "    AND LNAME.ATTR_ID = 5\n" +
            "ORDER BY apartment_number";

    String EXCEPTION_GET_ALL_VOTING_OPTIONS_BY_ANNOUNCEMENT_ID = "Can't get voting option by this announcement id: ";
    String EXCEPTION_CREATE_VOTING_OPTION = "Can't create voting option";
    String EXCEPTION_ADD_VOTE = "Can't add vote reference with voting option id: ";
    String EXCEPTION_GET_ALL_APARTMENTS_BY_VOTING_OPTION_ID = "Can't get apartments by this voting option id: ";

    Collection<VotingOption> getAllVotingOptionsByAnnouncementId(BigInteger announcementId) throws DaoAccessException;

    void createVotingOption(VotingOption votingOption) throws DaoAccessException;

    void addVote(BigInteger votingOptionId, BigInteger accountId) throws DaoAccessException;

    List<Apartment> getApartmentsByVotingOptionId(BigInteger id) throws DaoAccessException;
}
