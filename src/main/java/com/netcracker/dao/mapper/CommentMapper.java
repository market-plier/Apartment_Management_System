package com.netcracker.dao.mapper;


import com.netcracker.models.Comment;
import com.netcracker.models.PojoBuilder.AnnouncementBuilder;
import com.netcracker.models.PojoBuilder.ApartmentBuilder;
import com.netcracker.models.PojoBuilder.CommentBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Slf4j
public class CommentMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {

        Comment comment = new CommentBuilder()
                .witCommentId(new BigInteger(resultSet.getString("comment_id")))
                .withApartment(new ApartmentBuilder()
                        .withAccountId(new BigInteger(resultSet.getString("apartment_id")))
                        .withFirstName(resultSet.getString("first_name"))
                        .withLastName(resultSet.getString("last_name"))
                        .withApartmentNumber(new Integer(resultSet.getString("apartament_number")))
                        .build())
                .withBody(resultSet.getString("comment_body"))
                .withCreatedAt(resultSet.getDate("created_at"))
                .withAnnouncement(new AnnouncementBuilder()
                        .withAnnouncementId(new BigInteger(resultSet.getString("announcement_id")))
                        .build())
                .build();

        return comment;
    }
}
