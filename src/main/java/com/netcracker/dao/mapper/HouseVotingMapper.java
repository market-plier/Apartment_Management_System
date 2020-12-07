package com.netcracker.dao.mapper;

import com.netcracker.models.HouseVoting;
import com.netcracker.models.PojoBuilder.AnnouncementBuilder;
import com.netcracker.models.PojoBuilder.HouseVotingBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HouseVotingMapper implements RowMapper<HouseVoting> {
    @Override
    public HouseVoting mapRow(ResultSet rs, int rowNum) throws SQLException {
        HouseVoting houseVoting = new HouseVotingBuilder()
                .withHouseVotingId(new BigInteger(rs.getString("house_voting_id")))
                .withTitle(rs.getString("title"))
                .withAnnouncement(
                        new AnnouncementBuilder()
                        .withAnnouncementId(new BigInteger(rs.getString("announcement_id")))
                        .build()
                )
                .build();

        return houseVoting;
    }
}
