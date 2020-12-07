package com.netcracker.dao.impl;

import com.netcracker.dao.AnnouncementDao;
import com.netcracker.dao.mapper.AnnouncementMapper;
import com.netcracker.dao.mapper.CommentMapper;
import com.netcracker.models.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

import static com.netcracker.dao.CommentDao.GET_ALL_COMMENTS_BY_ANNOUNCEMENT_ID;

@Repository
@Transactional
public class AnnouncementDaoImpl implements AnnouncementDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Announcement> getAllAnnouncements() {
        return jdbcTemplate.query(GET_ALL_ANNOUNCEMENTS, new AnnouncementMapper());
    }

    @Override
    public Announcement getAnnouncementById(BigInteger id) {
        Announcement announcement = jdbcTemplate.queryForObject(GET_ANNOUNCEMENT_BY_ID, new AnnouncementMapper(), id);
        //announcement.setComments(jdbcTemplate.query(GET_ALL_COMMENTS_BY_ANNOUNCEMENT_ID, new CommentMapper()));

        return announcement;
    }

    @Override
    public void createAnnouncement(Announcement announcement) {
        jdbcTemplate.update(CREATE_ANNOUNCEMENT_OBJECT);
        jdbcTemplate.update(CREATE_ANNOUNCEMENT_ATTRIBUTES,
                announcement.getTitle(),
                announcement.getBody(),
                announcement.getIsOpened(),
                announcement.getCreatedAt());
    }

    @Override
    public void updateAnnouncement(Announcement announcement) {
        jdbcTemplate.update(UPDATE_ANNOUNCEMENT,
                announcement.getTitle(),
                announcement.getBody(),
                announcement.getIsOpened(),
                announcement.getCreatedAt(),
                announcement.getAnnouncementId());
    }

    @Override
    public void deleteAnnouncement(BigInteger id) {
        jdbcTemplate.update(DELETE_ANNOUNCEMENT, id);
    }
}
