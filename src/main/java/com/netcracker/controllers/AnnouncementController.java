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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
    public List<Announcement> getAllAnnouncements() throws DaoAccessException {
        return announcementService.getAllAnnouncements();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_OWNER')")
    public Announcement getAnnouncement(@NotNull(message = "Id cannot be null")
                                        @Positive(message = "Id cannot be negative")
                                        @PathVariable BigInteger id) throws DaoAccessException {
        return announcementService.getAnnouncementById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public Announcement createAnnouncement(@NotNull(message = "Announcement cannot be null")
                                           @RequestBody @Valid Announcement announcement) throws DaoAccessException {
        return announcementService.createAnnouncement(announcement);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public Announcement updateAnnouncement(@NotNull(message = "Announcement cannot be null")
                                           @RequestBody @Valid Announcement announcement,
                                           @NotNull(message = "Id cannot be null")
                                           @Positive(message = "Id cannot be negative")
                                           @PathVariable BigInteger id)
            throws DaoAccessException {
        return announcementService.updateAnnouncement(announcement);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public ResponseEntity deleteAnnouncement(@NotNull(message = "Id cannot be null")
                                             @Positive(message = "Id cannot be negative")
                                             @PathVariable BigInteger id)
            throws DaoAccessException {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
