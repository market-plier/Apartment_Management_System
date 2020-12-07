package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
@Data
public class Comment {
    private BigInteger commentId;
    private Announcement announcement;
    private Apartment apartment;
    private String body;
    private Date createdAt;

    public Comment(BigInteger commentId, Announcement announcement, Apartment apartment, String body, Date createdAt) {
        this.commentId = commentId;
        this.announcement = announcement;
        this.apartment = apartment;
        this.body = body;
        this.createdAt = createdAt;
    }
}
