package com.netcracker.model;

import lombok.Data;

import java.util.Date;

@Data
public class AbstractOperation {
    protected long operationId;
    protected double sum;
    protected Date createdAt;
}
