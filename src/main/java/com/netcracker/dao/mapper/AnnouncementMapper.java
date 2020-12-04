package com.netcracker.dao.mapper;

import com.netcracker.models.Announcement;
import com.netcracker.models.PojoBuilder.AnnouncementBuilder;
import com.netcracker.models.PojoBuilder.HouseVotingBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnnouncementMapper implements RowMapper<Announcement> {
    @Override
    public Announcement mapRow(ResultSet rs, int rowNum) throws SQLException {
        Announcement announcement = new AnnouncementBuilder()
                .withAnnouncementId(new BigInteger(rs.getString("announcement_id")))
                .withTitle(rs.getString("title"))
                .withBody(rs.getString("body"))
                .withIsOpened(rs.getBoolean("is_opened"))
                .withCreatedAt(rs.getDate("created_at"))
                .withHouseVoting(
                        new HouseVotingBuilder()
                        .withHouseVotingId(new BigInteger(rs.getString("house_voting_id")))
                        .withTitle(rs.getString("title"))
                        .build()
                )
                .build();

        return announcement;
    }
}
