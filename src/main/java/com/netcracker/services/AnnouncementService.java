package com.netcracker.services;

import com.netcracker.dao.AnnouncementDao;
import com.netcracker.dao.CommentDao;
import com.netcracker.dao.HouseVotingDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Announcement;
import com.netcracker.models.Comment;
import com.netcracker.models.HouseVoting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

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

    public List<Announcement> getAllAnnouncements() throws DaoAccessException {
        return announcementDao.getAllAnnouncements();
    }

    public Announcement getAnnouncementById(BigInteger id) throws DaoAccessException {
        Announcement announcement = announcementDao.getAnnouncementById(id);

        if (announcement.getHouseVoting() != null) {
            HouseVoting houseVoting = houseVotingDao.getHouseVotingByAnnouncementId(id);
            announcement.setHouseVoting(houseVoting);
        }

        List<Comment> comments = commentDao.getAllCommentsByAnnouncementId(id);
        announcement.setComments(comments);

        return announcement;
    }

    public Announcement updateAnnouncement(Announcement announcement) throws DaoAccessException {
        announcementDao.updateAnnouncement(announcement);
        return announcement;
    }

    public Announcement createAnnouncement(Announcement announcement) throws DaoAccessException {
        announcementDao.createAnnouncement(announcement);
        return announcement;
    }

    public void deleteAnnouncement(BigInteger id) throws DaoAccessException  {
        announcementDao.deleteAnnouncement(id);
    }
}
