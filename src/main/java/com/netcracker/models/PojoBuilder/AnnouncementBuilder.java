package com.netcracker.models.PojoBuilder;

import com.netcracker.models.Announcement;
import com.netcracker.models.Comment;
import com.netcracker.models.HouseVoting;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class AnnouncementBuilder {
    private BigInteger announcementId;
    private String title;
    private String body;
    private Boolean isOpened;
    private Date createdAt;
    private List<Comment> comments;
    private HouseVoting houseVoting;

    public AnnouncementBuilder withAnnouncementId(BigInteger announcementId) {
        this.announcementId = announcementId;
        return this;
    }

    public AnnouncementBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public AnnouncementBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public AnnouncementBuilder withIsOpened(Boolean isOpened) {
        this.isOpened = isOpened;
        return this;
    }

    public AnnouncementBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public AnnouncementBuilder withComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public AnnouncementBuilder withHouseVoting(HouseVoting houseVoting) {
        this.houseVoting = houseVoting;
        return this;
    }

    public Announcement build() {
        return new Announcement(announcementId, title, body, isOpened, createdAt, comments, houseVoting);
    }
}
