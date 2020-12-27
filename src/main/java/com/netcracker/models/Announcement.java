package com.netcracker.models;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class Announcement {
    @Positive
    private BigInteger announcementId;

    @NotBlank(message = "Announcement title cannot be blank")
    @Size(max = 255, message = "Announcement title size is not correct. Character length must be between 1 and 255")
    private String title;

    @Size(max = 1023, message = "Announcement body size is not correct. Character length must be between 1 and 255")
    private String body;

    @NotNull(message = "IsOpened value cannot be null")
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
