package com.netcracker.dao.impl;

import com.netcracker.dao.VotingOptionDao;
import com.netcracker.dao.mapper.VotingOptionMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Account;
import com.netcracker.models.VotingOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Collection;

@Slf4j
@Repository
@Transactional
public class VotingOptionDaoImpl implements VotingOptionDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Collection<VotingOption> getAllVotingOptionsByHouseVotingId(BigInteger id) throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_ALL_VOTING_OPTIONS_BY_HOUSE_VOTING_ID, new VotingOptionMapper(), id);
        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
            throw new DaoAccessException(EXCEPTION_GET_ALL_VOTING_OPTIONS_BY_ANNOUNCEMENT_ID, id, e.getCause());
        }

    }

    @Override
    public void createVotingOption(VotingOption votingOption) throws DaoAccessException {
        try {
            jdbcTemplate.update(CREATE_VOTING_OPTION_OBJECT,
                    votingOption.getHouseVoting().getHouseVotingId());
            jdbcTemplate.update(CREATE_VOTING_OPTION_ATTRIBUTES,
                    votingOption.getName());
        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
            throw new DaoAccessException(EXCEPTION_CREATE_VOTING_OPTION, e.getCause());
        }
    }

    @Override
    public void addVote(BigInteger votingOptionId, BigInteger accountId) throws DaoAccessException {
        try {
        jdbcTemplate.update(ADD_VOTE,
                votingOptionId,
                accountId);
        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
            throw new DaoAccessException(EXCEPTION_ADD_VOTED_ACCOUNT, votingOptionId, e.getCause());
        }
    }
}
