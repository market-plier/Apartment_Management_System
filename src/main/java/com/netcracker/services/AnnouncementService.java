package com.netcracker.services;

import com.netcracker.dao.AnnouncementDao;
import com.netcracker.dao.CommentDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.ErrorCodes;
import com.netcracker.exception.ObjectNotFoundException;
import com.netcracker.jobs.AnnouncementNotificationJob;
import com.netcracker.jobs.DebtNotificationJob;
import com.netcracker.models.Announcement;
import com.netcracker.models.Comment;
import com.netcracker.models.HouseVoting;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Log4j
@Service
@Transactional
public class AnnouncementService {
    @Autowired
    private AnnouncementDao announcementDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private HouseVotingService houseVotingService;
    @Autowired
    ScheduleJobService scheduleJobService;
    @Autowired
    private ApplicationContext context;

    public List<Announcement> getAllAnnouncements(String searchText, Date startDate, Date endDate, Boolean hasVoting)
            throws DaoAccessException {
        return announcementDao.getAllAnnouncements(searchText, startDate, endDate, hasVoting);
    }

    public Announcement getAnnouncementById(BigInteger id) throws DaoAccessException {
        Announcement announcement = announcementDao.getAnnouncementById(id);

        if (announcement == null) {
            ObjectNotFoundException exception = new ObjectNotFoundException(
                    "Announcement was not found with id: " + id,  ErrorCodes._NOT_FOUND_ANNOUNCEMENT);
            log.error("AnnouncementService method getAnnouncementById: " + exception.getMessage(), exception);
            throw exception;
        }

        if (announcement.getHouseVoting() != null) {
            HouseVoting houseVoting = houseVotingService.getHouseVotingByAnnouncementId(id);
            announcement.setHouseVoting(houseVoting);
        }

        List<Comment> comments = commentDao.getAllCommentsByAnnouncementId(id);
        announcement.setComments(comments);

        return announcement;
    }

    public Announcement updateAnnouncement(Announcement announcement) throws DaoAccessException {
        announcementDao.updateAnnouncement(announcement);
        BigInteger announcementId = announcement.getAnnouncementId();

        if (!announcement.getIsOpened()) {
            commentDao.deleteCommentsByAnnouncementId(announcementId);
        }

        return getAnnouncementById(announcementId);
    }

    public Announcement createAnnouncement(Announcement announcement) throws DaoAccessException {
        announcementDao.createAnnouncement(announcement);

        Announcement createdAnnouncement = announcementDao.getLatestAnnouncement();
        scheduleJobService.getScheduler().execute(context.getBean(AnnouncementNotificationJob.class).getJob(createdAnnouncement));

        return createdAnnouncement;
    }

    public void deleteAnnouncement(BigInteger id) throws DaoAccessException {
        announcementDao.deleteAnnouncement(id);
    }
}
