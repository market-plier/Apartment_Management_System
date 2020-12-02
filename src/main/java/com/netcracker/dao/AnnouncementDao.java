package com.netcracker.dao;

import com.netcracker.models.Announcement;

import java.math.BigInteger;
import java.util.List;

public interface AnnouncementDao {
    List<Announcement> getAllAnnouncements();

    Announcement getAnnouncementById(BigInteger id);

    void createAnnouncement(Announcement announcement);

    void updateAnnouncement(Announcement announcement);

    void deleteAnnouncement(BigInteger id);
}
