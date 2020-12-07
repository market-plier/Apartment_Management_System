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

        Comment comment = null;
        try {
            comment = new CommentBuilder()
                    .witCommentId(new BigInteger(resultSet.getString("comment_id")))
                    .withApartment(new ApartmentBuilder()
                            .withApartmentId(new BigInteger(resultSet.getString("apartament_id")))
                            .withApartmentFirstName(resultSet.getString("first_name"))
                            .withApartmentLastName(resultSet.getString("last_name"))
                            .withApartmentNumber(new BigInteger(resultSet.getString("apartament_number")))
                            .build())
                    .withBody(resultSet.getString("comment_body"))
                    .withCreatedAt(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS").parse(resultSet.getString("created_at")))
                    .withAnnouncement(new AnnouncementBuilder()
                            .withAnnouncementId(new BigInteger(resultSet.getString("announcement_id")))
                            .build())
                    .build();
        } catch (ParseException e) {
            log.warn(e.getMessage(), e);
        }
        return comment;
    }
}
