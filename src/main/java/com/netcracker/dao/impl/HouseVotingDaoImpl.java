package com.netcracker.dao.impl;

import com.netcracker.dao.HouseVotingDao;
import com.netcracker.dao.mapper.HouseVotingMapper;
import com.netcracker.models.HouseVoting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Repository
@Transactional
public class HouseVotingDaoImpl implements HouseVotingDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public HouseVoting getHouseVotingByAnnouncementId(BigInteger id) {
        HouseVoting houseVoting = jdbcTemplate.queryForObject(GET_HOUSE_VOTING_BY_ANNOUNCEMENT_ID, new HouseVotingMapper(), id);

        return houseVoting;
    }

    @Override
    public void createHouseVoting(HouseVoting houseVoting) {
        jdbcTemplate.update(CREATE_HOUSE_VOTING_OBJECT,
                houseVoting.getAnnouncement().getAnnouncementId());
        jdbcTemplate.update(CREATE_HOUSE_VOTING_ATTRIBUTES,
                houseVoting.getTitle());
    }

    @Override
    public void deleteHouseVoting(BigInteger id) {
        jdbcTemplate.update(DELETE_HOUSE_VOTING, id);
    }
}
