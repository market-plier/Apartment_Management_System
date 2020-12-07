package com.netcracker.models.PojoBuilder;

import com.netcracker.models.Announcement;
import com.netcracker.models.Apartment;
import com.netcracker.models.Comment;

import java.math.BigInteger;
import java.util.Date;

public class CommentBuilder {
    private BigInteger commentId;
    private Announcement announcement;
    private Apartment apartment;
    private String body;
    private Date createdAt;

    public CommentBuilder witCommentId(BigInteger commentId)
    {
        this.commentId = commentId;
        return this;
    }
    public CommentBuilder withAnnouncement(Announcement announcement)
    {
        this.announcement = announcement;
        return this;
    }

    public CommentBuilder withApartment (Apartment apartment)
    {
        this.apartment = apartment;
        return this;
    }

    public CommentBuilder withBody(String body)
    {
        this.body = body;
        return this;
    }
    public CommentBuilder withCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
        return this;
    }

    public Comment build()
    {
        return new Comment(commentId,announcement, apartment,body,createdAt);
    }

}
