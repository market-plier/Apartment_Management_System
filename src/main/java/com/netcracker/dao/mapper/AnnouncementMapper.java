package com.netcracker.dao.mapper;

import com.netcracker.models.Announcement;
import com.netcracker.models.HouseVoting;
import com.netcracker.models.PojoBuilder.AnnouncementBuilder;
import com.netcracker.models.PojoBuilder.HouseVotingBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnnouncementMapper implements RowMapper<Announcement> {
    @Override
    public Announcement mapRow(ResultSet rs, int rowNum) throws SQLException {
        HouseVoting houseVoting = null;
        if (rs.getString("house_voting_id") != null) {
            houseVoting = new HouseVotingBuilder()
                            .withHouseVotingId(rs.getString("house_voting_id") == null? null : new BigInteger(rs.getString("house_voting_id")))
                            .withTitle(rs.getString("house_voting_title"))
                            .build();
        }

        return new AnnouncementBuilder()
                .withAnnouncementId(new BigInteger(rs.getString("announcement_id")))
                .withTitle(rs.getString("announcement_title"))
                .withBody(rs.getString("body"))
                .withCreatedAt(rs.getTimestamp("created_at"))
                .withIsOpened(rs.getBoolean("is_opened"))
                .withHouseVoting(houseVoting)
                .build();
    }
}
