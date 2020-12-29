package com.netcracker.controllers;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.VotingOption;
import com.netcracker.secutity.jwt.JwtAccount;
import com.netcracker.services.VotingOptionService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
import java.util.Collection;

@Log4j
@RestController
@RequestMapping("/announcements/{announcementId}/house_voting/voting_options")
public class VotingOptionController {
    @Autowired
    private VotingOptionService votingOptionService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_OWNER')")
    public Collection<VotingOption> getAllVotingOptionsByAnnouncementId(@NotNull(message = "Id cannot be null")
                                                                        @Positive(message = "Id cannot be negative")
                                                                        @PathVariable BigInteger announcementId)
            throws DaoAccessException {
        return votingOptionService.getAllVotingOptionsByAnnouncementId(announcementId);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public VotingOption createVotingOption(@NotNull(message = "VotingOption cannot be null")
                                           @RequestBody @Valid VotingOption votingOption)
            throws DaoAccessException {
        return votingOptionService.createVotingOption(votingOption);
    }

    @PostMapping("/{votingOptionId}/add_vote")
    @PreAuthorize("hasAnyRole('ROLE_OWNER')")
    public ResponseEntity addVote(@AuthenticationPrincipal JwtAccount account,
                                  @NotNull(message = "announcementId cannot be null")
                                  @Positive(message = "announcementId cannot be negative")
                                  @PathVariable BigInteger announcementId,
                                  @NotNull(message = "votingOptionId cannot be null")
                                  @Positive(message = "votingOptionId cannot be negative")
                                  @PathVariable BigInteger votingOptionId)
            throws IllegalArgumentException, DaoAccessException {
        votingOptionService.addVote(announcementId, votingOptionId, account.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
