package com.netcracker.model;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Announcement {
    private long announcementId;
    private String title;
    private String body;
    private boolean isOpened;
    private Date createdAt;
    private List<Comment> comments;
    private HouseVoting housevoting;
}
