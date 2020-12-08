package com.netcracker.dao;

import com.netcracker.models.Comment;

import java.math.BigInteger;
import java.util.List;

public interface CommentDao {

     String GET_ALL_COMMENTS_BY_ANNOUNCEMENT_ID = "SELECT COMM_BODY.OBJECT_ID comment_id, COMM_BODY.VALUE comment_body, COMM_CREATED_AT.DATE_VALUE created_at,\n" +
            "ATTR_NUMBER_OF_APART.OBJECT_ID apartment_id, ATTR_NUMBER_OF_APART.VALUE apartment_number,\n" +
            "ATTR_LAST_NAME.VALUE last_name, ATTR_FIRST_NAME.VALUE first_name,OBJ_COMM.PARENT_ID announcement_id\n" +
            "FROM   ATTRIBUTES COMM_BODY,ATTRIBUTES COMM_CREATED_AT,ATTRIBUTES ATTR_FIRST_NAME,\n" +
            "ATTRIBUTES ATTR_LAST_NAME,ATTRIBUTES ATTR_NUMBER_OF_APART\n" +
            ",OBJREFERENCE OBJREF_APAR, OBJECTS OBJ_COMM\n" +
            "WHERE OBJ_COMM.PARENT_ID = ?\n" +
            "AND OBJ_COMM.OBJECT_ID = COMM_BODY.OBJECT_ID\n" +
            "AND COMM_BODY.ATTR_ID = 11\n" +
            "AND COMM_BODY.OBJECT_ID = COMM_CREATED_AT.OBJECT_ID\n" +
            "AND COMM_CREATED_AT.ATTR_ID = 12\n" +
            "AND OBJREF_APAR.ATTR_ID = 29\n" +
            "AND OBJREF_APAR.OBJECT_ID = OBJ_COMM.OBJECT_ID\n" +
            "AND OBJREF_APAR.REFERENCE = ATTR_LAST_NAME.OBJECT_ID\n" +
            "AND ATTR_LAST_NAME.ATTR_ID = 5\n" +
            "AND ATTR_LAST_NAME.OBJECT_ID = ATTR_FIRST_NAME.OBJECT_ID\n" +
            "AND ATTR_FIRST_NAME.ATTR_ID = 4\n" +
            "AND ATTR_NUMBER_OF_APART.OBJECT_ID = ATTR_LAST_NAME.OBJECT_ID\n" +
            "AND ATTR_NUMBER_OF_APART.ATTR_ID = 18";

     String CREATE_COMMENT_OBJECT = "MERGE INTO OBJECTS OBJ_COMMENT\n" +
             "USING (SELECT ? parent_id, ? object_type_id, 'COMMENT_'||seq_obj_curr() name FROM DUAL) NEW_COMM\n" +
             "ON (OBJ_COMMENT.OBJECT_ID = seq_obj_next())\n" +
             "WHEN NOT MATCHED THEN\n" +
             "INSERT (object_id, parent_id, object_type_id, name, description)\n" +
             "VALUES (seq_obj_curr(),NEW_COMM.parent_id,NEW_COMM.object_type_id,NEW_COMM.name,NULL)\n" +
             "WHEN MATCHED THEN\n" +
             "UPDATE\n" +
             "SET OBJ_COMMENT.PARENT_ID = NEW_COMM.parent_id,\n" +
             "OBJ_COMMENT.OBJECT_TYPE_ID = NEW_COMM.object_type_id,\n" +
             "OBJ_COMMENT.NAME = NEW_COMM.name\n" +
             "WHERE OBJ_COMMENT.PARENT_ID <> NEW_COMM.parent_id\n" +
             "AND OBJ_COMMENT.OBJECT_TYPE_ID <> NEW_COMM.object_type_id\n" +
             "AND OBJ_COMMENT.NAME <> NEW_COMM.name";

     String CREATE_COMMENT_ATTRIBUTES = "MERGE INTO ATTRIBUTES ATTR_COMM\n" +
             "USING (SELECT ? ATTR_ID, ? VALUE, NULL DATE_VALUE FROM DUAL\n" +
             "UNION ALL SELECT ? ATTR_ID, NULL VALUE, systimestamp DATE_VALUE FROM DUAL) NEW_ATTR_COMM\n" +
             "ON ( ATTR_COMM.OBJECT_ID = seq_obj_curr() AND ATTR_COMM.ATTR_ID = NEW_ATTR_COMM.ATTR_ID)\n" +
             "WHEN NOT MATCHED THEN\n" +
             "INSERT (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)\n" +
             "VALUES (NEW_ATTR_COMM.ATTR_ID, seq_obj_curr(), NEW_ATTR_COMM.VALUE, NEW_ATTR_COMM.DATE_VALUE, NULL)\n" +
             "WHEN MATCHED THEN\n" +
             "UPDATE\n" +
             "SET ATTR_COMM.VALUE = NEW_ATTR_COMM.VALUE,\n" +
             "ATTR_COMM.DATE_VALUE = NEW_ATTR_COMM.DATE_VALUE\n" +
             "WHERE NEW_ATTR_COMM.VALUE <> ATTR_COMM.VALUE OR ATTR_COMM.DATE_VALUE<>DATE_VALUE";

     String CREATE_COMMENT_OBJREFERENCE = "MERGE INTO OBJREFERENCE OBJREF_COMM\n" +
             "USING (SELECT ? ATTR_ID, ? REFERENCE, seq_obj_curr() COMMENT_ID FROM DUAL) NEW_COMM_REF\n" +
             "ON ( OBJREF_COMM.OBJECT_ID = NEW_COMM_REF.COMMENT_ID AND NEW_COMM_REF.ATTR_ID  = OBJREF_COMM.ATTR_ID\n" +
             "AND NEW_COMM_REF.REFERENCE = OBJREF_COMM.REFERENCE)\n" +
             "WHEN NOT MATCHED THEN\n" +
             "INSERT (ATTR_ID, OBJECT_ID, REFERENCE)\n" +
             "VALUES (NEW_COMM_REF.ATTR_ID , NEW_COMM_REF.COMMENT_ID, NEW_COMM_REF.REFERENCE)";


    String UPDATE_COMMENT_BY_ID = "MERGE INTO ATTRIBUTES ATTR_COMMENT\n" +
            "USING (SELECT 11 ATTR_ID, ? VALUE  FROM DUAL) UPD\n" +
            "ON ( ATTR_COMMENT.OBJECT_ID = ? AND UPD.ATTR_ID = ATTR_COMMENT.ATTR_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "UPDATE SET ATTR_COMMENT.VALUE = UPD.VALUE\n" +
            "WHERE ATTR_COMMENT.VALUE<> UPD.VALUE";

   String DELETE_COMMENT = "DELETE OBJECTS WHERE OBJECT_ID = ?";

    String GET_COMMENT_BY_ID = "SELECT COMM_BODY.OBJECT_ID comment_id, COMM_BODY.VALUE comment_body, COMM_CREATED_AT.DATE_VALUE created_at,\n" +
            "ATTR_NUMBER_OF_APART.OBJECT_ID apartment_id, ATTR_NUMBER_OF_APART.VALUE apartment_number,\n" +
            "ATTR_LAST_NAME.VALUE last_name, ATTR_FIRST_NAME.VALUE first_name,OBJ_COMM.PARENT_ID announcement_id\n" +
            "FROM   ATTRIBUTES COMM_BODY,ATTRIBUTES COMM_CREATED_AT,ATTRIBUTES ATTR_FIRST_NAME,\n" +
            "ATTRIBUTES ATTR_LAST_NAME,ATTRIBUTES ATTR_NUMBER_OF_APART\n" +
            ",OBJREFERENCE OBJREF_APAR, OBJECTS OBJ_COMM\n" +
            "WHERE  OBJ_COMM.OBJECT_ID = ?\n" +
            "AND OBJ_COMM.OBJECT_TYPE_ID = 4\n" +
            "AND COMM_BODY.OBJECT_ID = OBJ_COMM.OBJECT_ID\n" +
            "AND COMM_BODY.ATTR_ID = 11\n" +
            "AND COMM_BODY.OBJECT_ID = COMM_CREATED_AT.OBJECT_ID\n" +
            "AND COMM_CREATED_AT.ATTR_ID = 12\n" +
            "AND OBJREF_APAR.ATTR_ID = 29\n" +
            "AND OBJREF_APAR.OBJECT_ID = OBJ_COMM.OBJECT_ID\n" +
            "AND OBJREF_APAR.REFERENCE = ATTR_LAST_NAME.OBJECT_ID\n" +
            "AND ATTR_LAST_NAME.ATTR_ID = 5\n" +
            "AND ATTR_LAST_NAME.OBJECT_ID = ATTR_FIRST_NAME.OBJECT_ID\n" +
            "AND ATTR_FIRST_NAME.ATTR_ID = 4\n" +
            "AND ATTR_NUMBER_OF_APART.OBJECT_ID = ATTR_LAST_NAME.OBJECT_ID\n" +
            "AND ATTR_NUMBER_OF_APART.ATTR_ID = 18";


    List<Comment> getAllCommentsByAnnouncementId(BigInteger announcementId);
    Comment getCommentById(BigInteger commentId);
    void updateComment(Comment comment);
    void createComment(Comment comment);

    void deleteComment(BigInteger commentId);
}
