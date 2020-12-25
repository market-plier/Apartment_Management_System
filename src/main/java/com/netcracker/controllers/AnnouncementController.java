package com.netcracker.controllers;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Announcement;
import com.netcracker.services.AnnouncementService;
import lombok.extern.log4j.Log4j;
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
import java.util.List;

@Log4j
@RestController
@RequestMapping("/announcements")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_OWNER')")
    public List<Announcement> getAllAnnouncements() throws DaoAccessException, NullPointerException {
        return announcementService.getAllAnnouncements();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_OWNER')")
    public Announcement getAnnouncement(@PathVariable BigInteger id)
            throws DaoAccessException, NullPointerException {
        return announcementService.getAnnouncementById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public Announcement createAnnouncement(@RequestBody @Valid Announcement announcement)
            throws DaoAccessException, NullPointerException {
        return announcementService.createAnnouncement(announcement);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public Announcement updateAnnouncement(@RequestBody @Valid Announcement announcement,
                                           @PathVariable BigInteger id)
            throws DaoAccessException, NullPointerException {
        return announcementService.updateAnnouncement(announcement);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public ResponseEntity deleteAnnouncement(@PathVariable BigInteger id) throws DaoAccessException, NullPointerException {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
