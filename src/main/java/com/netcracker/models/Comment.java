package com.netcracker.models;

import lombok.Data;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;
@Data
public class Comment {

    private BigInteger commentId;
    @NotNull(message = "announcement cant be null")
    private Announcement announcement;

    private Apartment apartment;

    @NotBlank
    @Size(min = 1, message = "Length comment must be more than 1: ")
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
