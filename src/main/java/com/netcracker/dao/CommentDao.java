package com.netcracker.dao;

import com.netcracker.models.Comment;

import java.math.BigInteger;
import java.util.List;

public interface CommentDao {

     String GET_ALL_COMMENTS_BY_ANNOUNCEMENT_ID = "SELECT COMM_BODY.OBJECT_ID comment_id, COMM_BODY.VALUE comment_body, COMM_CREATED_AT.VALUE created_at,\n" +
            "       ATTR_NUMBER_OF_APART.OBJECT_ID apartament_id, ATTR_NUMBER_OF_APART.VALUE apartament_number,\n" +
            "       ATTR_LAST_NAME.VALUE last_name, ATTR_FIRST_NAME.VALUE first_name,OBJ_COMM.PARENT_ID announcement_id\n" +
            "FROM   ATTRIBUTES COMM_BODY,ATTRIBUTES COMM_CREATED_AT,ATTRIBUTES ATTR_FIRST_NAME,\n" +
            "       ATTRIBUTES ATTR_LAST_NAME,ATTRIBUTES ATTR_NUMBER_OF_APART\n" +
            "      ,OBJREFERENCE OBJREF_APAR, OBJECTS OBJ_COMM\n" +
            "WHERE OBJ_COMM.PARENT_ID = ?\n" +
            "    AND OBJ_COMM.OBJECT_ID = COMM_BODY.OBJECT_ID\n" +
            "    AND COMM_BODY.ATTR_ID = 11\n" +
            "    AND COMM_BODY.OBJECT_ID = COMM_CREATED_AT.OBJECT_ID\n" +
            "    AND COMM_CREATED_AT.ATTR_ID = 12\n" +
            "    AND OBJREF_APAR.ATTR_ID = 29\n" +
            "    AND OBJREF_APAR.OBJECT_ID = OBJ_COMM.OBJECT_ID\n" +
            "    AND OBJREF_APAR.REFERENCE = ATTR_LAST_NAME.OBJECT_ID\n" +
            "    AND ATTR_LAST_NAME.ATTR_ID = 5\n" +
            "    AND ATTR_LAST_NAME.OBJECT_ID = ATTR_FIRST_NAME.OBJECT_ID\n" +
            "    AND ATTR_FIRST_NAME.ATTR_ID = 4\n" +
            "    AND ATTR_NUMBER_OF_APART.OBJECT_ID = ATTR_LAST_NAME.OBJECT_ID\n" +
            "    AND ATTR_NUMBER_OF_APART.ATTR_ID = 18";

    List<Comment> getAllCommentsByAnnouncementId(BigInteger announcementId);
    Comment getCommentById(BigInteger commentId);
    void updateComment(Comment comment);
    void createComment(Comment comment);

    void deleteComment(BigInteger commentId);
}
