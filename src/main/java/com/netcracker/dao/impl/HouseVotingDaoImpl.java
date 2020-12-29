package com.netcracker.dao.impl;

import com.netcracker.dao.HouseVotingDao;
import com.netcracker.dao.mapper.HouseVotingMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.DaoAccessExceptionBuilder;
import com.netcracker.exception.ErrorCodes;
import com.netcracker.models.HouseVoting;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Log4j
@Repository
@Transactional
public class HouseVotingDaoImpl implements HouseVotingDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public HouseVoting getHouseVotingByAnnouncementId(BigInteger announcementId) throws DaoAccessException {
        try {
            return jdbcTemplate.queryForObject(GET_HOUSE_VOTING_BY_ANNOUNCEMENT_ID, new HouseVotingMapper(), announcementId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            DaoAccessException accessException = new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_HOUSE_VOTING)
                    .withMessage(EXCEPTION_GET_HOUSE_VOTING_BY_ANNOUNCEMENT_ID)
                    .withId(announcementId)
                    .withCause(e.getCause())
                    .build();
            log.error("HouseVotingDaoImpl method getHouseVotingByAnnouncementId: " + accessException.getMessage());
            throw accessException;
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
            DaoAccessException accessException = new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_INSERT_HOUSE_VOTING)
                    .withMessage(EXCEPTION_CREATE_HOUSE_VOTING)
                    .withCause(e.getCause())
                    .build();
            log.error("HouseVotingDaoImpl method createHouseVoting: " + accessException.getMessage());
            throw accessException;
        }
    }

    @Override
    public void deleteHouseVoting(BigInteger announcementId) throws DaoAccessException {
        try {
            jdbcTemplate.update(DELETE_HOUSE_VOTING, announcementId);
        } catch (DataAccessException e) {
            DaoAccessException accessException = new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_DELETE_HOUSE_VOTING)
                    .withMessage(EXCEPTION_DELETE_HOUSE_VOTING)
                    .withId(announcementId)
                    .withCause(e.getCause())
                    .build();
            log.error("HouseVotingDaoImpl method deleteHouseVoting: " + accessException.getMessage());
            throw accessException;
        }
    }
}
