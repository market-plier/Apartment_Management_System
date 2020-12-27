package com.netcracker.dao.mapper;

import com.netcracker.models.PojoBuilder.HouseVotingBuilder;
import com.netcracker.models.PojoBuilder.VotingOptionBuilder;
import com.netcracker.models.VotingOption;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VotingOptionMapper implements RowMapper<VotingOption> {
    @Override
    public VotingOption mapRow(ResultSet rs, int rowNum) throws SQLException {
        VotingOption votingOption = new VotingOptionBuilder()
                .withVotingOptionId(new BigInteger(rs.getString("voting_option_id")))
                .withName(rs.getString("name"))
                .withCount(Integer.valueOf(rs.getString("count")))
                .withHouseVoting(
                        new HouseVotingBuilder()
                        .withHouseVotingId(new BigInteger(rs.getString("house_voting_id")))
                        .build()
                )
                .build();
        return votingOption;
    }
}
