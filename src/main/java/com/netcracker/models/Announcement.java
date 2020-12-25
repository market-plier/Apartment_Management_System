package com.netcracker.models;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class Announcement {

    private BigInteger announcementId;

    @NotBlank(message = "Announcement title cannot be empty")
    private String title;

    private String body;
    private Boolean isOpened;
    private Date createdAt;

    private List<Comment> comments;
    private HouseVoting houseVoting;

    public Announcement(BigInteger announcementId, String title, String body, Boolean isOpened,
                        Date createdAt, List<Comment> comments, HouseVoting houseVoting) {
        this.announcementId = announcementId;
        this.title = title;
        this.body = body;
        this.isOpened = isOpened;
        this.createdAt = createdAt;
        this.comments = comments;
        this.houseVoting = houseVoting;
    }
}
