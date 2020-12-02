package com.netcracker.models;

import java.math.BigInteger;
import java.util.Date;

public class Comment {
    private BigInteger commentId;
    private Announcement announcement;
    private String body;
    private Date createdAt;
}
