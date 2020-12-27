package com.netcracker.dao.impl;

import com.netcracker.dao.AnnouncementDao;
import com.netcracker.dao.mapper.AnnouncementMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.DaoAccessExceptionBuilder;
import com.netcracker.exception.ErrorCodes;
import com.netcracker.models.Announcement;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Log4j
@Repository
@Transactional
public class AnnouncementDaoImpl implements AnnouncementDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Announcement> getAllAnnouncements() throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_ALL_ANNOUNCEMENTS, new AnnouncementMapper());
        } catch (DataAccessException e) {
            DaoAccessException accessException = new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_ANNOUNCEMENT)
                    .withMessage(EXCEPTION_GET_ALL_ANNOUNCEMENTS)
                    .withCause(e.getCause())
                    .build();
            log.error("AnnouncementDaoImpl method getAllAnnouncements: " + accessException.getMessage());
            throw accessException;
        }
    }

    @Override
    public Announcement getAnnouncementById(BigInteger id) throws DaoAccessException {
        try {
            Announcement announcement = jdbcTemplate.queryForObject(GET_ANNOUNCEMENT_BY_ID, new AnnouncementMapper(), id);
            return announcement;
        } catch (DataAccessException e) {
            DaoAccessException accessException = new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_ANNOUNCEMENT)
                    .withMessage(EXCEPTION_GET_ANNOUNCEMENT_BY_ID)
                    .withId(id)
                    .withCause(e.getCause())
                    .build();
            log.error("AnnouncementDaoImpl method getAnnouncementById: " + accessException.getMessage());
            throw accessException;
        }
    }

    @Override
    public Announcement getLatestAnnouncement() throws DaoAccessException {
        try {
            Announcement announcement = jdbcTemplate.queryForObject(GET_LATEST_ANNOUNCEMENT, new AnnouncementMapper());
            return announcement;
        } catch (DataAccessException e) {
            DaoAccessException accessException = new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_ANNOUNCEMENT)
                    .withMessage(EXCEPTION_GET_LATEST_ANNOUNCEMENT)
                    .withCause(e.getCause())
                    .build();
            log.error("AnnouncementDaoImpl method getLatestAnnouncement: " + accessException.getMessage());
            throw accessException;
        }
    }

    @Override
    public void createAnnouncement(Announcement announcement) throws DaoAccessException {
        try {
            jdbcTemplate.update(CREATE_ANNOUNCEMENT_OBJECT);
            jdbcTemplate.update(CREATE_ANNOUNCEMENT_ATTRIBUTES,
                    announcement.getTitle(),
                    announcement.getBody(),
                    announcement.getIsOpened() ? "1" : "0");
        } catch (DataAccessException e) {
            DaoAccessException accessException = new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_INSERT_ANNOUNCEMENT)
                    .withMessage(EXCEPTION_CREATE_ANNOUNCEMENT)
                    .withCause(e.getCause())
                    .build();
            log.error("AnnouncementDaoImpl method createAnnouncement: " + accessException.getMessage());
            throw accessException;
        }
    }

    @Override
    public void updateAnnouncement(Announcement announcement) throws DaoAccessException {
        try {
            jdbcTemplate.update(UPDATE_ANNOUNCEMENT,
                    announcement.getTitle(),
                    announcement.getBody(),
                    announcement.getIsOpened() ? "1" : "0",
                    announcement.getAnnouncementId());
        } catch (DataAccessException e) {
            DaoAccessException accessException = new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_UPDATE_ANNOUNCEMENT)
                    .withMessage(EXCEPTION_UPDATE_ANNOUNCEMENT)
                    .withCause(e.getCause())
                    .build();
            log.error("AnnouncementDaoImpl method updateAnnouncement: " + accessException.getMessage());
            throw accessException;
        }
    }

    @Override
    public void deleteAnnouncement(BigInteger id) throws DaoAccessException {
        try {
            jdbcTemplate.update(DELETE_ANNOUNCEMENT, id);
        } catch (DataAccessException e) {
            DaoAccessException accessException = new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_DELETE_ANNOUNCEMENT)
                    .withMessage(EXCEPTION_DELETE_ANNOUNCEMENT)
                    .withId(id)
                    .withCause(e.getCause())
                    .build();
            log.error("AnnouncementDaoImpl method deleteAnnouncement: " + accessException.getMessage());
            throw accessException;
        }
    }
}
