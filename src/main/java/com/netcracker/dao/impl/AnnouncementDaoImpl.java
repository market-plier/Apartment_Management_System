package com.netcracker.dao.impl;

import com.netcracker.dao.AnnouncementDao;
import com.netcracker.dao.mapper.AnnouncementMapper;
import com.netcracker.exception.DaoAccessException;
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
            log.error(e.getMessage(), e);
            throw new DaoAccessException(EXCEPTION_GET_ALL_ANNOUNCEMENTS, e.getCause());
        }
    }

    @Override
    public Announcement getAnnouncementById(BigInteger id) throws DaoAccessException {
        try {
            Announcement announcement = jdbcTemplate.queryForObject(GET_ANNOUNCEMENT_BY_ID, new AnnouncementMapper(), id);
            return announcement;
        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
            throw new DaoAccessException(EXCEPTION_GET_ANNOUNCEMENT_BY_ID, id, e.getCause());
        }
    }

    @Override
    public void createAnnouncement(Announcement announcement) throws DaoAccessException {
        try {
            jdbcTemplate.update(CREATE_ANNOUNCEMENT_OBJECT);
            jdbcTemplate.update(CREATE_ANNOUNCEMENT_ATTRIBUTES,
                    announcement.getTitle(),
                    announcement.getBody(),
                    announcement.getIsOpened(),
                    announcement.getCreatedAt());
        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
            throw new DaoAccessException(EXCEPTION_CREATE_ANNOUNCEMENT, e.getCause());
        }
    }

    @Override
    public void updateAnnouncement(Announcement announcement) throws DaoAccessException {
        try {
            jdbcTemplate.update(UPDATE_ANNOUNCEMENT,
                    announcement.getTitle(),
                    announcement.getBody(),
                    announcement.getIsOpened(),
                    announcement.getCreatedAt(),
                    announcement.getAnnouncementId());
        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
            throw new DaoAccessException(EXCEPTION_UPDATE_ANNOUNCEMENT, announcement.getAnnouncementId(), e.getCause());
        }
    }

    @Override
    public void deleteAnnouncement(BigInteger id) throws DaoAccessException {
        try {
            jdbcTemplate.update(DELETE_ANNOUNCEMENT, id);
        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
            throw new DaoAccessException(EXCEPTION_DELETE_ANNOUNCEMENT, id, e.getCause());
        }
    }
}
