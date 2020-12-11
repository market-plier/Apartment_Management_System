package com.netcracker.dao;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Account;
import com.netcracker.models.VotingOption;

import java.math.BigInteger;
import java.util.Collection;

public interface VotingOptionDao {
    String GET_ALL_VOTING_OPTIONS_BY_ANNOUNCEMENT_ID =
            "SELECT VOTING_OPTION.OBJECT_ID voting_option_id,\n" +
            "VOTING_OPTION_NAME.VALUE name,\n" +
            "HVOTING.OBJECT_ID house_voting_id\n" +
            "FROM OBJECTS VOTING_OPTION, OBJECTS HVOTING,\n" +
            "ATTRIBUTES VOTING_OPTION_NAME\n" +
            "    WHERE VOTING_OPTION.OBJECT_TYPE_ID = 6\n" +
            "    AND HVOTING.PARENT_ID = ?\n" +
            "    AND VOTING_OPTION.PARENT_ID = HVOTING.OBJECT_ID\n" +
            "    AND VOTING_OPTION_NAME.ATTR_ID = 14\n" +
            "    AND VOTING_OPTION_NAME.OBJECT_ID = VOTING_OPTION.OBJECT_ID";

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
            "   VALUES(new.ATTR_ID, new.OBJECT_ID, new.VALUE);";

    String ADD_VOTED_ACCOUNT =
            "MERGE INTO OBJREFERENCE old\n" +
            "USING (SELECT 30 ATTR_ID, ? OBJECT_ID, ? REFERENCE FROM DUAL) new\n" +
            "ON (old.ATTR_ID = new.ATTR_ID AND old.OBJECT_ID = new.OBJECT_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "   UPDATE SET old.REFERENCE = new.REFERENCE\n" +
            "   WHERE old.REFERENCE <> new.REFERENCE\n" +
            "WHEN NOT MATCHED THEN\n" +
            "   INSERT(old.ATTR_ID,old.OBJECT_ID, old.REFERENCE)\n" +
            "   VALUES(new.ATTR_ID,new.OBJECT_ID, new.REFERENCE);";

    String EXCEPTION_GET_ALL_VOTING_OPTIONS_BY_ANNOUNCEMENT_ID = "Can't get voting option with this announcement id: ";
    String EXCEPTION_CREATE_VOTING_OPTION = "Can't create voting option";
    String EXCEPTION_ADD_VOTED_ACCOUNT = "Can't add vote reference with voting option id: ";

    Collection<VotingOption> getAllVotingOptionsByAnnouncementId(BigInteger id) throws DaoAccessException;

    void createVotingOption(VotingOption votingOption) throws DaoAccessException;

    void addVotedAccount(VotingOption votingOption, Account account) throws DaoAccessException;
}
