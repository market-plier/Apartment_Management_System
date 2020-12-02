package com.netcracker.models;


import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class Announcement {
    private BigInteger announcementId;
    private String title;
    private String body;
    private boolean isOpened;
    private Date createdAt;
    private List<Comment> comments;
    private HouseVoting housevoting;
}
