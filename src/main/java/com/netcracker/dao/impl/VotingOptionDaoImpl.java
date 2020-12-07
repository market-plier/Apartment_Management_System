package com.netcracker.dao.impl;

import com.netcracker.dao.VotingOptionDao;
import com.netcracker.dao.mapper.AnnouncementMapper;
import com.netcracker.dao.mapper.VotingOptionMapper;
import com.netcracker.models.Account;
import com.netcracker.models.VotingOption;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Collection<VotingOption> getAllVotingOptionsByAnnouncementId(BigInteger id) {
        return jdbcTemplate.query(GET_ALL_VOTING_OPTIONS_BY_ANNOUNCEMENT_ID, new VotingOptionMapper(), id);
    }

    @Override
    public void createVotingOption(VotingOption votingOption) {
        jdbcTemplate.update(CREATE_VOTING_OPTION_OBJECT,
                votingOption.getHouseVoting().getHouseVotingId());
        jdbcTemplate.update(CREATE_VOTING_OPTION_ATTRIBUTES,
                votingOption.getName());
    }

    @Override
    public void addVotedAccount(VotingOption votingOption, Account account) {
        jdbcTemplate.update(ADD_VOTED_ACCOUNT,
                votingOption.getVotingOptionId(),
                account.getAccountId());
    }
}
