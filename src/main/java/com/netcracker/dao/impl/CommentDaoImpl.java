package com.netcracker.dao.impl;

import com.netcracker.dao.CommentDao;
import com.netcracker.dao.Constants;
import com.netcracker.dao.mapper.CommentMapper;
import com.netcracker.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Comment> getAllCommentsByAnnouncementId(BigInteger announcementId) {
        return jdbcTemplate.query(CommentDao.GET_ALL_COMMENTS_BY_ANNOUNCEMENT_ID,new CommentMapper(), announcementId);
    }

    @Override
    public Comment getCommentById(BigInteger commentId) {
        return jdbcTemplate.queryForObject(GET_COMMENT_BY_ID,new CommentMapper(),commentId);
    }

    @Override
    public void updateComment(Comment comment) {
        jdbcTemplate.update(UPDATE_COMMENT_BY_ID,comment.getBody(),comment.getCommentId());
    }

    @Override
    public void createComment(Comment comment) {
        jdbcTemplate.update(CREATE_COMMENT_OBJECT, comment.getAnnouncement().getAnnouncementId(), Constants.COMMENT_TYPE_ID);
        jdbcTemplate.update(CREATE_COMMENT_OBJREFERENCE,Constants.COMMENT_ATTR_REF_TO_APARTMENT,comment.getApartment().getAccountId());
        jdbcTemplate.update(CREATE_COMMENT_ATTRIBUTES,Constants.COMMENT_ATTR_BODY_ID, comment.getBody(),Constants.COMMENT_ATTR_CREATED_AT_ID);
    }

    @Override
    public void deleteComment(BigInteger commentId) {
        jdbcTemplate.update(DELETE_COMMENT,commentId);
    }
}
