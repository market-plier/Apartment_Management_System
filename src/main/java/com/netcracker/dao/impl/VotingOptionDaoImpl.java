package com.netcracker.dao.impl;

import com.netcracker.dao.VotingOptionDao;
import com.netcracker.dao.mapper.VotingOptionMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Account;
import com.netcracker.models.VotingOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Collection;

@Repository
@Transactional
public class VotingOptionDaoImpl implements VotingOptionDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Collection<VotingOption> getAllVotingOptionsByAnnouncementId(BigInteger id) throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_ALL_VOTING_OPTIONS_BY_ANNOUNCEMENT_ID, new VotingOptionMapper(), id);
        } catch (DataAccessException e) {
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
            throw new DaoAccessException(EXCEPTION_CREATE_VOTING_OPTION, e.getCause());
        }
    }

    @Override
    public void addVotedAccount(VotingOption votingOption, Account account) throws DaoAccessException {
        try {
        jdbcTemplate.update(ADD_VOTED_ACCOUNT,
                votingOption.getVotingOptionId(),
                account.getAccountId());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_ADD_VOTED_ACCOUNT, votingOption.getVotingOptionId(), e.getCause());
        }
    }
}
