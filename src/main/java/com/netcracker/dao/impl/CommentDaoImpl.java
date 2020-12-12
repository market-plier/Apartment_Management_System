package com.netcracker.dao.impl;

import com.netcracker.dao.CommentDao;
import com.netcracker.dao.Constants;
import com.netcracker.dao.mapper.CommentMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public class CommentDaoImpl implements CommentDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Comment> getAllCommentsByAnnouncementId(BigInteger announcementId) throws DaoAccessException {
        try {
            return jdbcTemplate.query(CommentDao.GET_ALL_COMMENTS_BY_ANNOUNCEMENT_ID, new CommentMapper(), announcementId);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_ALL_COMMENTS_BY_ANNOUNCEMENT_ID, announcementId, e.getCause());
        }

    }

    @Override
    public Comment getCommentById(BigInteger commentId) throws DaoAccessException {
        try {
            return jdbcTemplate.queryForObject(GET_COMMENT_BY_ID, new CommentMapper(), commentId);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_COMMENT_BY_ID, commentId, e.getCause());
        }

    }

    @Override
    public void updateComment(Comment comment) throws DaoAccessException {
        try {
            jdbcTemplate.update(UPDATE_COMMENT_BY_ID, comment.getBody(), comment.getCommentId());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_UPDATE_COMMENT, comment.getCommentId(), e.getCause());
        }

    }

    @Override
    public void createComment(Comment comment) throws DaoAccessException {
        try {
            jdbcTemplate.update(CREATE_COMMENT_OBJECT,
                    comment.getAnnouncement().getAnnouncementId(),
                    Constants.COMMENT_TYPE_ID);
            jdbcTemplate.update(CREATE_COMMENT_OBJREFERENCE,
                    Constants.COMMENT_ATTR_REF_TO_APARTMENT,
                    comment.getApartment().getAccountId());
            jdbcTemplate.update(CREATE_COMMENT_ATTRIBUTES,
                    Constants.COMMENT_ATTR_BODY_ID, comment.getBody(),
                    Constants.COMMENT_ATTR_CREATED_AT_ID);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_CREATE_COMMENT, e.getCause());
        }

    }

    @Override
    public void deleteComment(BigInteger commentId) throws DaoAccessException {
        try {
            jdbcTemplate.update(DELETE_COMMENT, commentId);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_DELETE_COMMENT, commentId, e.getCause());
        }

    }
}
