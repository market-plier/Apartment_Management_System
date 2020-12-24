package com.netcracker.controllers;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.VotingOption;
import com.netcracker.secutity.jwt.JwtAccount;
import com.netcracker.services.VotingOptionService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Collection;

@Log4j
@RestController
@RequestMapping("/announcements/{announcementId}/house_votings/{houseVotingId}/voting_options")
public class VotingOptionController {
    @Autowired
    private VotingOptionService votingOptionService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_OWNER')")
    public Collection<VotingOption> getAllVotingOptionsByHouseVotingId(@PathVariable BigInteger houseVotingId) throws DaoAccessException, NullPointerException {
        return votingOptionService.getAllVotingOptionsByHouseVotingId(houseVotingId);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public VotingOption createVotingOption(@RequestBody @Valid VotingOption votingOption) throws DaoAccessException, NullPointerException {
        return votingOptionService.createVotingOption(votingOption);
    }

    @PostMapping("/{votingOptionId}/add_vote")
    @PreAuthorize("hasAnyRole('ROLE_OWNER')")
    public void addVote(@AuthenticationPrincipal JwtAccount account,  @PathVariable BigInteger houseVotingId, @PathVariable BigInteger votingOptionId) throws IllegalArgumentException, DaoAccessException, NullPointerException {
        votingOptionService.addVote(houseVotingId, votingOptionId, account.getId());
    }
}
