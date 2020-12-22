package com.netcracker.models;

import lombok.Data;

@Data
public class NotificationBuildInfo {
    private String title;
    private String body;

    public NotificationBuildInfo(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
