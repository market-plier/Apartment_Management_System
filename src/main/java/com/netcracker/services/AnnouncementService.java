package com.netcracker.services;

import com.netcracker.dao.AnnouncementDao;
import com.netcracker.dao.CommentDao;
import com.netcracker.dao.HouseVotingDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Announcement;
import com.netcracker.models.Comment;
import com.netcracker.models.HouseVoting;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Log4j
@Service
public class AnnouncementService{
    @Autowired
    private AnnouncementDao announcementDao;
    @Autowired
    private HouseVotingDao houseVotingDao;
    @Autowired
    private CommentDao commentDao;

    public AnnouncementService(AnnouncementDao announcementDao) {
        this.announcementDao = announcementDao;
    }

    public List<Announcement> getAllAnnouncements() throws DaoAccessException, NullPointerException {
        try {
            return announcementDao.getAllAnnouncements();
        } catch (NullPointerException e) {
            log.error("AnnouncementService method getAllAnnouncements: " + e.getMessage(), e);
            throw e;
        }
    }

    public Announcement getAnnouncementById(BigInteger id) throws DaoAccessException, NullPointerException  {
        try {
            Announcement announcement = announcementDao.getAnnouncementById(id);

            if (announcement.getHouseVoting() != null) {
                HouseVoting houseVoting = houseVotingDao.getHouseVotingByAnnouncementId(id);
                announcement.setHouseVoting(houseVoting);
            }

            List<Comment> comments = commentDao.getAllCommentsByAnnouncementId(id);
            announcement.setComments(comments);

            return announcement;
        } catch (NullPointerException e) {
            log.error("AnnouncementService method getAnnouncementById: " + e.getMessage(), e);
            throw e;
        }
    }

    public Announcement updateAnnouncement(Announcement announcement) throws DaoAccessException, NullPointerException  {
        try {
            announcementDao.updateAnnouncement(announcement);
            return announcement;
        } catch (NullPointerException e) {
            log.error("AnnouncementService method getAnnouncementById: " + e.getMessage(), e);
            throw e;
        }
    }

    public Announcement createAnnouncement(Announcement announcement) throws DaoAccessException, NullPointerException  {
        try {
            announcementDao.createAnnouncement(announcement);
            return announcement;
        } catch (NullPointerException e) {
            log.error("AnnouncementService method getAnnouncementById: " + e.getMessage(), e);
            throw e;
        }
    }

    public void deleteAnnouncement(BigInteger id) throws DaoAccessException, NullPointerException   {
        try {
            announcementDao.deleteAnnouncement(id);
        } catch (NullPointerException e) {
            log.error("AnnouncementService method getAnnouncementById: " + e.getMessage(), e);
            throw e;
        }
    }
}
