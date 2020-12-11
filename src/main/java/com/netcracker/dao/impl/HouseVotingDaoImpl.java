package com.netcracker.dao.impl;

import com.netcracker.dao.HouseVotingDao;
import com.netcracker.dao.mapper.HouseVotingMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.HouseVoting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public HouseVoting getHouseVotingByAnnouncementId(BigInteger id) throws DaoAccessException {
        try {
            HouseVoting houseVoting = jdbcTemplate.queryForObject(GET_HOUSE_VOTING_BY_ANNOUNCEMENT_ID, new HouseVotingMapper(), id);
            return houseVoting;
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_HOUSE_VOTING_BY_ANNOUNCEMENT_ID, id, e.getCause());
        }
    }

    @Override
    public void createHouseVoting(HouseVoting houseVoting) throws DaoAccessException {
        try {
            jdbcTemplate.update(CREATE_HOUSE_VOTING_OBJECT,
                    houseVoting.getAnnouncement().getAnnouncementId());
            jdbcTemplate.update(CREATE_HOUSE_VOTING_ATTRIBUTES,
                    houseVoting.getTitle());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_CREATE_HOUSE_VOTING, e.getCause());
        }
    }

    @Override
    public void deleteHouseVoting(BigInteger id) throws DaoAccessException {
        try {
            jdbcTemplate.update(DELETE_HOUSE_VOTING, id);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_DELETE_HOUSE_VOTING, id, e.getCause());
        }
    }
}
