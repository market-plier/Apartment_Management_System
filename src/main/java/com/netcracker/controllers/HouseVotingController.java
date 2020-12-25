package com.netcracker.controllers;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.HouseVoting;
import com.netcracker.services.HouseVotingService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigInteger;

@Log4j
@RestController
@RequestMapping("/announcements/{announcementId}/house_voting")
public class HouseVotingController {
    @Autowired
    private HouseVotingService houseVotingService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_OWNER')")
    public HouseVoting getHouseVotingByAnnouncementId(@PathVariable BigInteger announcementId)
            throws DaoAccessException, NullPointerException {
        return houseVotingService.getHouseVotingByAnnouncementId(announcementId);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public HouseVoting createHouseVoting(@RequestBody @Valid HouseVoting houseVoting)
            throws DaoAccessException, NullPointerException {
        return houseVotingService.createHouseVoting(houseVoting);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public ResponseEntity deleteHouseVoting(@PathVariable BigInteger announcementId)
            throws DaoAccessException, NullPointerException {
        houseVotingService.deleteHouseVoting(announcementId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
